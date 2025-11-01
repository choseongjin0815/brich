var auth = null;
var startUrl = null;
var currentPswrd = null;
var newPswrd = null;
var newPswrdConfirm = null;
$().ready(function() {
    
    //권한에 따른 경로 설정
    auth = $(".account-menu-box").data("auth");
    console.log(auth);
    if(auth === 1004) {
        startUrl = "/adv/account";
    }
    else if (auth === 1002 || auth === 1003) {
        startUrl = "/blgr/account";
    }
    
    //계정 관리 페이지
    $(".account-info").on("click", function() {
        window.location.href = startUrl + "/info";
    });

    //비밀번호 재설정 페이지
    $(".reset-password").on("click", function() {
        window.location.href = startUrl + "/reset-password";
    });
    
    //구독 정보 페이지
    $(".sub-info").on("click", function() {
        window.location.href = startUrl + "/subscription-info";
    });
    
    //새로운 비밀번호로 수정
    $(".reset-password-confirm").on("click", function() {
        currentPswrd = $(".current-pswrd").val();
        newPswrd = $(".new-pswrd").val();
        newPswrdConfirm = $(".new-pswrd-confirm").val();
        
        console.log(currentPswrd, newPswrd, newPswrdConfirm);
        
        
        $.post(startUrl
             + "/reset-password"
            ,{currentPswrd : currentPswrd
                , newPswrd : newPswrd
                , newPswrdConfirm : newPswrdConfirm}
            , function(response){
             console.log(response);
            if(!response.error) {
                alert(response.body.result);
                window.location.reload();
            }
        });
    });
});