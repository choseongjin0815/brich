$().ready(function() {
    $(".role-box").on("click",function() {
       var role = $(this).data("role") 
       window.location.href = "/terms/" + role;  
    });
    
    $("#check-all").on("click", function() {
       if($(this).is(':checked')) {
          $("#personal").prop('checked', true);
          $("#term").prop('checked', true);
       }
       else {
          $("#personal").prop('checked', false);
          $("#term").prop('checked', false);
       }
    });
    $("input[type='checkbox'][name='check']").on("change", function () {
          var length = $("input[type='checkbox'][name='check']:not(:checked)").length;
      if (length === 0) {
        $("#check-all").prop("checked", true);
      } else {
          $("#check-all").prop("checked", false);
      }
    });
    
    $(".regist-btn").on("click", function() {
         $(".user-regist-form").submit();
    });
    
    $(".next-btn").on("click", function() {
        var role = $(this).data("role"); 
       if($("#personal").is(':checked') && $("#term").is(':checked')) {
          console.log($("#personal").val())
          window.location.href = "/regist/" + role;
       }
       else {
        alert("모두 동의해야합니다!!!");
       } 
    });  
});
