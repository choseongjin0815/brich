/**
 * 
 */
$().ready(function() {
    
    var rejectModal = $("#rejectModal");
    var rejectModalTitle = $(".reject-modal-title");
    var rejectModalInput = $(".reject-modal-input");
    var rejectModalCloseBtn = $(".reject-modal-close-btn");
    var rejectModalNextBtn = $(".reject-modal-Next-btn");
    
    var rejectBtnAreaOne = $(".modal-btn-area-one");
    var rejectBtnAreaTwo = $(".modal-btn-area-two");
    
    /* (반려) 반려 버튼 클릭 시 모달 열기 */
    $(".reject-btn").on("click", function() {
        openModalAction("REJECT");
    });
    
    /* (반려) 모달 닫기 버튼 클릭 시 창 닫기 */
    $(rejectModalCloseBtn).on("click", function() {
        closeModalAction("REJECT");
    });

    /* (반려) 바깥 배경 클릭 시 모달 창 닫기 */
    rejectModal.on("click", function(event) {
        // 클릭한 배경의 id 비교
        if(event.target.id === "rejectModal") {
            closeRejectModal();
        }
    });
    
    // 모달 닫기 액션
    var closeModalAction = function(type) {
        switch(type) {
            case "REJECT": 
                          closeRejectModal();
                  default:
                          return;
        }
    };
    
    /* (반려) 모달 닫기 */
    var closeRejectModal = function() {
        rejectModal.hide();
        rejectModalTitle.text("반려 사유를 작성해 주세요.");
        rejectModalInput.val("");
    };
    
    // 모달 열기 액션
    var openModalAction = function(type) {
        switch(type) {
            case "REJECT": 
                          openRejectModal();
                  default:
                          return;
        }
    };
    
    /* (반려) 모달 열기 */
    var openRejectModal = function() {
        rejectModal.show();
        rejectBtnAreaTwo.hide();
    }
    
    /* (반려) 모달 1차 확인 버튼 클릭 시 */
    $(rejectModalNextBtn).on("click", function() {
        
        rejectBtnAreaOne.hide();
        rejectBtnAreaTwo.show();
        
        
        rejectModalTitle.text("아래와 같은 사유로 정말 반려하시겠습니까?");
        rejectModalInput.prop("readonly", true);
    });
    
});
