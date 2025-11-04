$().ready(function() {
    //문의 목록으로 이동
    $(".inqr-list").on("click", function() {
        window.location.href = "/help/inqr/list";
    });
    //문의 작성으로 이동
    $(".inqr-write").on("click", function() {
        window.location.href = "/help/inqr/write"; 
    });
    //도움말 목록으로 이동
    $(".faq-list").on("click", function() {
        window.location.href = "/help/faq"; 
    });
    
    
    // 문의하기
    $(".btn-submit").on("click", function(){
       // 필수 입력 검증
       var inqrTitle = $("#inqrTitle").val().trim();
       var inqrCtg = $("#inqrCtg").val();
       var inqrCn = $("#inqrCn").val().trim();
       
       if(!inqrTitle) {
           alert("문의 제목을 입력해주세요.");
           $("#inqrTitle").focus();
           return;
       }
       
       if(!inqrCtg) {
           alert("문의 유형을 선택해주세요.");
           $("#inqrCtg").focus();
           return;
       }
       
       if(!inqrCn) {
           alert("문의 내용을 입력해주세요.");
           $("#inqrCn").focus();
           return;
       }
       
       // FormData 객체 생성 (파일 업로드용)
       var formData = new FormData();
       formData.append("inqrTitle", inqrTitle);
       formData.append("inqrUsrId", $("#InqrUsrIdHidden").val());
       formData.append("inqrCtg", inqrCtg);
       formData.append("inqrCn", inqrCn);
       
       // 파일 추가
       var files = $("#inqrFiles")[0].files;
       if(files.length > 0) {
           for(var i = 0; i < files.length; i++) {
               formData.append("file", files[i]);
           }
       }
       
       // AJAX POST 요청
       $.ajax({
           url: "/help/inqr/write",
           type: "POST",
           data: formData,
           processData: false, 
           contentType: false, 
           success: function(response) {
               alert(response.body);
               window.location.href = "/help/inqr/list";
           },
           error: function(error) {
               alert(response.body);
               console.error("Error:", error);
           }
       });
    });
});