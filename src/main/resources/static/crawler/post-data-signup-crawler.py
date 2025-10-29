from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import StaleElementReferenceException
from selenium.common.exceptions import TimeoutException
from bs4 import BeautifulSoup
from datetime import datetime
from datetime import datetime, timedelta
from fake_useragent import UserAgent

import json
import requests
import re
import time 
import math
import sys

# # Chrome 드라이버 옵션 설정
options = Options()
options.add_argument("--headless")  # 화면 없이 실행
options.add_argument("--disable-gpu")
options.add_argument("--no-sandbox")
ua = UserAgent()

# random user-agent 설정
def get_pc_user_agent():
    while True:
        candidate = ua.random
        if not re.search(r'Mobile|Android|iPhone', candidate, re.I):
            return candidate
user_agent = get_pc_user_agent()
options.add_argument(f'user-agent={user_agent}')



# service = Service("/path/to/chromedriver")  # chromedriver 경로
driver = webdriver.Chrome(options)

def is_toplist_open(driver):
    try:
        wrapper = driver.find_element(By.ID, "toplistWrapper")

        # display, height, aria 상태 감지
        display = driver.execute_script("return window.getComputedStyle(arguments[0]).display;", wrapper)
        height = driver.execute_script("return arguments[0].offsetHeight;", wrapper)
        aria_hidden = wrapper.get_attribute("aria-hidden") or wrapper.get_attribute("area-hidden")

        visible = wrapper.is_displayed()
        aria_open = (aria_hidden is None) or (aria_hidden.lower() == "false")

        # 🔧 완화된 조건: display != none 또는 height > 50
        return (display != "none" or height > 50) and aria_open and visible

    except Exception:
        return False


def ensure_toplist_open(driver, timeout=10):
    try:
        # 먼저 span 텍스트로 상태 판단
        span_text = driver.find_element(By.ID, "toplistSpanBlind").text.strip()
        print(f"현재 목록 상태 텍스트: {span_text}")

        # 이미 열려 있는 경우
        if "목록닫기" in span_text:
            print("목록이 이미 열려 있음 → 클릭 생략")
            return
        time.sleep(3)  # 안정 대기
        print("목록이 닫혀 있음 → 목록 열기 클릭")
        toggle_btn = WebDriverWait(driver, timeout).until(
            EC.element_to_be_clickable((By.CSS_SELECTOR, "a._toggleTopList"))
        )

        driver.execute_script("arguments[0].scrollIntoView(true);", toggle_btn)
        driver.execute_script("arguments[0].click();", toggle_btn)

        # 열릴 때까지 대기 (span 텍스트가 '목록닫기'로 바뀌는지 확인)
        WebDriverWait(driver, timeout).until(
            lambda d: "목록닫기" in d.find_element(By.ID, "toplistSpanBlind").text
        )

        print("목록 열림 확인 완료")

    except TimeoutException:
        print("⚠️ Timeout: 목록이 열리지 않았습니다. (이미 열려 있거나 목록 없음일 수 있음)")

# 네이버 블로그 진입
blg_url = sys.argv[1]
driver.get(blg_url) # 블로그 ID를 인자로 받음
wait = WebDriverWait(driver, 5)

# 1. iframe이 로드될 때까지 대기 후 전환
wait.until(EC.frame_to_be_available_and_switch_to_it((By.ID, "mainFrame")))

blog_link = WebDriverWait(driver, 3).until(
    EC.element_to_be_clickable((By.XPATH, "//a[contains(@href, 'PostList.naver') and contains(@class, 'itemfont') and contains(@class, '_doNclick') and contains(@class, '_param(false|blog|)')]"))
    ,print("블로그 탭 클릭")
)
blog_link.click()

# ✅ 전체보기 클릭
try:
    all_posts_link = WebDriverWait(driver, 5).until(
        EC.element_to_be_clickable((By.XPATH, "//a[@id='category0' and contains(text(), '전체보기')]"))
    )
    driver.execute_script("arguments[0].click();", all_posts_link)
    print("전체보기 클릭 완료")
except TimeoutException:
    print("전체보기 버튼을 찾을 수 없습니다.")




# 1️⃣ 목록 열림 상태 보장
ensure_toplist_open(driver)

# 2️⃣ 잠깐 대기 (목록 DOM 완전히 갱신될 때까지)
time.sleep(0.5)

# 3️⃣ 목록이 열린 상태의 HTML로 새로 파싱
soup = BeautifulSoup(driver.page_source, 'html.parser')

# 4️⃣ 페이지 개수 추출
page_count_elem = soup.select_one('h4.category_title.pcol2')
numeric_chars = [char for char in page_count_elem.text if char.isdigit()]
numeric_string = "".join(numeric_chars)


# list_size = soup.select_one('#listCountView').text
# list_size = re.findall(r'\d+', list_size)[0]
links = set()  # ✅ set으로 중복 방지
total_pages = math.ceil(int(numeric_string) / 5)

for page_num in range(1, 5):
    # ✅ 현재 페이지 HTML 새로 파싱
    soup = BeautifulSoup(driver.page_source, 'html.parser')
    
    # ✅ 링크 수집 (절대경로 + 정확한 클래스 필터)
    for a in soup.find_all('a', href=True):
        href = a['href']
        classes = a.get('class', [])
        if (
            href.startswith('https://blog.naver.com/PostView.naver?blogId=') and
            all(c in classes for c in ['pcol2', '_setTop', '_setTopListUrl']) and
            not a.has_attr('logno') and
            not a.has_attr('onclick')
        ):
            links.add(href)  # set이라 중복 안 됨

    print(f"[PAGE {page_num}] 수집된 링크 수: {len(links)}")

    # ✅ 다음 페이지 버튼 클릭
    next_xpath = f"//a[contains(@class,'_goPageTop') and contains(@class,'_param({page_num+1})')]"
    try:
        next_button = WebDriverWait(driver, 5).until(
            EC.element_to_be_clickable((By.XPATH, next_xpath))
        )
        driver.execute_script("arguments[0].click();", next_button)
        
        # ✅ 페이지가 실제로 바뀔 때까지 대기
        WebDriverWait(driver, 10).until(EC.staleness_of(next_button))
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, "table.blog2_list"))
        )
    except TimeoutException:
        print(f"페이지 {page_num}에서 다음 버튼을 찾을 수 없음 (마지막 페이지일 수 있음).")
        break

print(f"총 고유 링크 수: {len(links)}")
for l in sorted(links):
    print(l)
   


# 클릭 후의 HTML 가져오기
html = driver.page_source
print(html[:1000])  # 앞부분만 출력해보기

count = 1
results = []
for idx, post_url in enumerate(links):
    driver.get(post_url)
    WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.TAG_NAME, 'iframe')))
    try:
        iframe = driver.find_element(By.ID, "mainFrame")
        driver.switch_to.frame(iframe)
    except Exception:
        pass
    
    # 프레임 전환 후
    WebDriverWait(driver, 10).until(lambda d: d.execute_script("return document.readyState") == "complete")

    soup = BeautifulSoup(driver.page_source, 'html.parser')

    like_elem = soup.select_one('span.u_likeit_text._count.num')
    # 공감 수가 로드되지 않았을 경우 다시 시도
    if not like_elem or not like_elem.text.strip():
        soup = BeautifulSoup(driver.page_source, 'html.parser')
        like_elem = soup.select_one('span.u_likeit_text._count.num')

    like_count = like_elem.text.strip() if like_elem and like_elem.text.strip() else 'N/A'

    # 날짜
    post_elem = soup.select_one('span.se_publishDate.pcol2')
    post_date = post_elem.text.strip() if post_elem else 'N/A'

    now = datetime.now()
    if re.search(r'(시간|분|일)\s*전', post_date):
        post_datetime = now
    else:
        try:
            post_datetime = datetime.strptime(post_date, "%Y. %m. %d. %H:%M")
            if now - post_datetime < timedelta(days=7):
                print(f"최근 7일 이내 포스트 → 제외: {post_date}")
                continue  # 리스트에 추가하지 않고 다음 포스트로 넘어감
        except ValueError:
            try:
                post_datetime = datetime.strptime(post_date, "%Y. %m. %d.")
            except ValueError:
                print(f"날짜 파싱 실패: {post_date}")
                continue

    # post_date_str = post_datetime.strftime("%Y-%m-%d")

    # 댓글
    comment_elem = soup.select_one('em._commentCount')
    comment_count = comment_elem.text.strip() if comment_elem else 'N/A'

    results.append({
        'index': idx,
        'url': post_url,
        'date': post_date,
        'likes': like_count,
        'comments': comment_count
    })

    print(f"{post_url} | 날짜: {post_date} | 공감: {like_count} | 댓글: {comment_count} | 인덱스: {idx+1}")

# ====== 날짜 정렬 (문자열 → datetime 변환 후 최신순 정렬) ======
def parse_date_safe(s: str) -> datetime:
    s = s.replace(".", "-").replace(" ", "").strip()
    try:
        return datetime.strptime(s, "%Y-%m-%d")
    except ValueError:
        # "YYYY-MM-DDHH:MM" 같이 붙은 경우
        try:
            return datetime.strptime(s[:10], "%Y-%m-%d")
        except Exception:
            return datetime.min

results.sort(key=lambda x: parse_date_safe(x["date"]), reverse=True)
url = "http://localhost:8080/api/results"

def safe_int(value):
    try:
        return int(value)
    except:
        return 0
data = {
    "blgAddrs": blg_url,   
    "postList": [           
        {
            "pstUrl": r["url"],
            "pstCmnt": safe_int(r["comments"]),
            "pstLk": safe_int(r["likes"]),
            "pstdDt": r["date"]
        }
        for r in results
    ]
}
print("=== 전송 데이터 미리보기 ===")
print(json.dumps(data, indent=2, ensure_ascii=False))
print("==========================")

response = requests.post(
    url,
    headers={"Content-Type": "application/json"},
    json=data  
)
driver.quit()

