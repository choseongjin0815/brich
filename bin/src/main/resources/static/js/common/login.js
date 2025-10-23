$().ready(function () {  
    $(".login-button").on("click", function() {
        console.log("asdasd")
        //현재 url 체크 
        var currentUrl = window.location.pathname;
        //로그인 버튼 눌러서 로그인 페이지에 접근한 경우 
        if(currentUrl === "/login") {
            currentUrl = "/main";//게시글 목록
        }
        $("#login").attr("action", "/login?nextUrl=" + currentUrl).submit();
    })
})