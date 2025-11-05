from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import StaleElementReferenceException
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from bs4 import BeautifulSoup
from datetime import datetime
from fake_useragent import UserAgent
import requests
import sys
import re
import json

def to_mobile_blog_url(url: str) -> str:
    return url.replace("://blog.naver.com/", "://m.blog.naver.com/")

# Chrome 드라이버 옵션 설정
options = Options()

# service = Service("/path/to/chromedriver")  # chromedriver 경로
driver = webdriver.Chrome(options)

blog_url = sys.argv[1]

driver.get(blog_url)

# XPath 선택자 사용
neighbor_xpath = '//*[@id="widget-stat"]/div/ul/li[1]/em'
scrap_xpath = '//*[@id="widget-stat"]/div/ul/li[3]/em'
WebDriverWait(driver, 10).until(
    EC.frame_to_be_available_and_switch_to_it((By.ID, "mainFrame"))
)
# 요소가 나타날 때까지 대기
neighbor_elem = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, neighbor_xpath))
)
scrap_elem = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, scrap_xpath))
)
# 텍스트 추출
neighbor_value = int(neighbor_elem.text.strip().replace(",", ""))
scrap_value = int(scrap_elem.text.strip().replace(",", ""))


print("서로 이웃 수: "+ str(neighbor_value))
print("스크랩 수: "+ str(scrap_value))

# 모바일 주소로 변환
mobile_url = to_mobile_blog_url(blog_url)
driver.get(mobile_url)

# count__T3YO8 요소가 나타날 때까지 대기
count_div = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.CSS_SELECTOR, "div.count__T3YO8"))
)

# 텍스트 추출
text = count_div.text.strip()

# "전체" 뒤 숫자만 추출
match = re.search(r"전체\s*([\d,]+)", text)
if match:
    total_str = match.group(1)
    total_num = int(total_str.replace(",", ""))
    print(f"전체 방문자 수: {total_num}")
else:
    print("⚠️ 전체 방문자 수를 찾지 못했습니다.")

driver.quit()


payload = {
    "blgAddrs": blog_url,
    "blgNghbrCnt": neighbor_value,
    "scrpCnt": scrap_value,
    "ttlVstrCnt": total_num,
}

print("=== 전송 데이터 미리보기 ===")
print(json.dumps(payload, indent=2, ensure_ascii=False))
print("==========================")

url = "http://localhost:8080/api/stats"
try:
    response = requests.post(
        url,
        headers={"Content-Type": "application/json"},
        json=payload
    )
    if response.status_code == 200:
        print("success")
    else:
        print(f"failed: {response.status_code} {response.text}")
except Exception as e:
    print("failed with: ", e)