/**
 * 
 */
$().ready(function() {
    
    var adminId = $("#adminId").val();
    var targetCdId;
    var targetCdNm;
    var targetSrt;
    var currentModalType;    // 현재 어떤 모달 사용 중인지
    
    var categoryModal = $("#categoryModal");
    categoryModal.hide();    // 모달 틀 가리기
    
    var modalTitle = $("#title");    // 모달 제목 내용
    var modalCloseBtn = $(".modal-close-btn");    // 모달 닫기 버튼
    var modalSaveBtn = $(".modal-save-btn");    // 모달 확인 버튼
    
    /* 카테고리 추가 */
    var addCategoryButton = $(".add-btn");    // 카테고리 추가 버튼
    var addCategoryArea =$("#addCategoryArea");    // 카테고리 추가 영역
    var newCategoryName = $("#newCategoryName");    // 추가시킬 카테고리 이름 input
    $(addCategoryButton).on("click", function() {
        modalAction("ADD", null);
    });
    
    /* 카테고리 분할 */
    var divCategoryButton = $(".div-btn");    // 카테고리 분할 버튼
    var divCategoryArea = $("#divCategoryArea");    // 카테고리 분할 영역
    $(divCategoryButton).on("click", function() {
        modalAction("DIV", $(this));
    });
    
    /* 카테고리 병합 */
    var mergeCategoryButton = $(".merge-btn");    // 카테고리 병합 버튼
    var mergeCategoryArea = $("#mergeCategoryArea");    // 카테고리 분할 영역
    $(mergeCategoryButton).on("click", function() {
        modalAction("MERGE", $(this));
    });
    
    /* 카테고리 순서 변경 (저장 버튼 눌러서 적용) */
    
    /* 모달 닫기 버튼 클릭 시 창 닫기 */
    modalCloseBtn.on("click", function() {
        closeModal();
    });
    
    /* 바깥 배경 클릭 시 모달 창 닫기 */
    categoryModal.on("click", function(event) {
        // 클릭한 배경의 id 비교
        if(event.target.id === "categoryModal") {
            closeModal();
        }
    });
    
    /* 모달 확인 버튼 클릭 시 (기능 타입에 맞는 함수 호출) */
    modalSaveBtn.on("click", function() {
        switch(currentModalType) {
            case "ADD": 
                        addCategory();
                        break;
            case "DIV": 
                        divCategory();
                        break;
            case "MERGE": 
                        mergeCategory();
                        break;
                default: 
                        alert("모달/기능 타입 식별 불가");
        }
    });
    
    /* 모달 열기 전, select 태그 내 option 값 채우기 */
    // type: 기능 종류
    // currentCdId: 선택된 기능에 맞는 카테고리 ID
    var loadCategoryList = function(type, currentCdId) {
        
        var selectedList = (type === "DIV") 
                         ? $("[name='divActiveCategoryList']") 
                         : $("[name='mergeActiveCategoryList']");
        
        // 기존 옵션 초기화 (잔여물 제거 느낌...)
        selectedList.empty().append("<option value=''>데이터 불러오는 중...</option>");
        
        var url = (type === "DIV") 
                ? "/admin/category-manage/modal-list/children?parentCdId=" + currentCdId
                : "/admin/category-manage/modal-list/parent?excludeCdId=" + currentCdId;
        
        $.ajax({
            url: url, 
            type: "GET", 
            dataType: "json", 
            
            success: function(categoryList) {
                
                selectedList.empty();
                
                var defaultOptionText = (type === "DIV")
                                      ? "하위 카테고리 선택"
                                      : "상위 카테고리 선택";
                
                selectedList.append("<option value='' selected disabled>" + defaultOptionText + "</option>")
                
                if(categoryList && categoryList.length > 0) {
                    $.each(categoryList, function(index, category) {
                        
                        // 병합의 경우 자신의 이름은 제외하고 출력한다.
                        if(type === "MERGE" && category.cdId === currentCdId) {
                            return true;
                        }
                        selectedList.append("<option name='categoryOption' value='" + category.cdId + "'>" 
                                                + category.cdNm + 
                                            "</option>");
                        
                    });
                }
                else {
                    selectedList.append("<option value='' disabled>사용 가능 카테고리 없음</option>");
                }
            } 
            // error 생략
        });
    };
    
    /* 클릭된 버튼 종류에 따라 활성화 시킬 모달 선택 */
    var modalAction = function(type, button) {
        
        // 입력만 받으면 되는 거라 바로 모달 열고 return
        if(type === "ADD") {
            targetCdId = "";
            targetCdNm = "";
            targetSrt = ""; 
            openModal(type, null);
            return;
        }
        
        // 분할or병합일 경우 해당 버튼의 부모 쪽에서 데이터 추출
        var infoDiv = button.closest(".category-info");
        
        var hiddenInput = infoDiv.find("input[type='hidden'][data-parent-cd-id]").first();
        
        var data = {};
        data.cdId = hiddenInput.data("parentCdId");
        data.cdNm = infoDiv.find(".category-parent").text();
        var srtText = infoDiv.find(".category-srt span").text().trim();
        data.srt = srtText.replace("순위:", "").trim();
        
        if(!data.cdId || !data.cdNm || !data.srt) {
            alert("카테고리 정보를 읽어올 수 없음.");
            return;
        }
        
        targetCdId = data.cdId;
        targetCdNm = data.cdNm;
        targetSrt = data.srt;
        
        // 해당 카테고리 정보를 가지고 가서 기능에 맞는 모달 열기
        openModal(type, data);
    };
    
    /* 모달 열기 */
    var openModal = function(type, data) {
        
        addCategoryArea.hide();
        divCategoryArea.hide();
        mergeCategoryArea.hide();
        
        currentModalType = type;
        
        switch(type) {
            
            case "ADD": 
                        modalTitle.text("카테고리 추가");
                        addCategoryArea.show();
                        newCategoryName.val("").focus();
                        break;
            case "DIV": 
                        modalTitle.text("카테고리 분할");
                        divCategoryArea.children(".targetCategoryName").text(data.cdNm);
                        loadCategoryList("DIV", data.cdId);
                        divCategoryArea.show();
                        break;
            case "MERGE": 
                        modalTitle.text("카테고리 병합");
                        mergeCategoryArea.children(".targetCategoryName").text(data.cdNm);
                        loadCategoryList("MERGE", data.cdId);
                        mergeCategoryArea.show();
                        break;
                default: 
                        alert("모달 타입 식별 불가: " + type);
                        return;
        }
        categoryModal.show();
    };
    
    /* 모달 닫기 */
    var closeModal = function() {
        categoryModal.hide();
        currentModalType = "";
        targetCdId = "";
        targetCdNm = "";
        targetSrt = "";
        modalTitle.text("");
        newCategoryName.val("");
        
        $("[name='divActiveCategoryList']")
         .empty()
         .append('<option value="" disabled>하위 카테고리 선택</option>')
         .prop('multiple', false); 
         
        $("[name='mergeActiveCategoryList']")
         .empty()
         .append('<option value="" disabled>상위 카테고리 선택</option>');
         
        modalSaveBtn.prop("disabled", false);
    };
    
    /* 카테고리 추가 (Ajax 전송) */
    var addCategory = function() {
        
        var categoryName = newCategoryName.val().trim();
        
        if(categoryName === "") {
            alert("추가할 카테고리 이름을 입력해 주세요.");
            newCategoryName.val("");
            newCategoryName.focus();
            return;
        }
        modalSaveBtn.prop("disabled", true);
        
        var dataToSend = {
            adminId : adminId, 
            cdNm : categoryName
        }
        
        $.ajax({
            url: "/admin/category-manage/add", 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(dataToSend), 
            
            success: function(response) {
                alert(categoryName + " 카테고리 추가 완료");
                location.reload();
            }, 
            complete: function() {
                modalSaveBtn.prop("disabled", false);
            }
        });
    };
    
    /* 카테고리 분할 (Ajax 전송) */
    var divCategory = function() {
        
        var selectedOptionCdId = $("option[name='categoryOption']:selected").val();
        
        if(!selectedOptionCdId) {
            alert("분할할 카테고리를 선택해 주세요.");
            return;
        }
        
        modalSaveBtn.prop("disabled", true).text("분할 요청 중...");
        
        var dataToSend = {
            adminId : adminId, 
            cdId : selectedOptionCdId
        };
        
        
    };
    
    /* 카테고리 병합 (Ajax 전송) */
    
    
});