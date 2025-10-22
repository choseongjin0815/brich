/**
 * 회원 리스트 탭 제어
 */
$().ready(function() {
    
    var tables = [$("#all-user-tbl"), $("#blogger-tbl"), $("#adtr-tbl")];
    
    $("table").hide();
    $("#all-user-tbl").show();
    
    $("input[name='admin-user-tab'").on("change", function() {
       
       var selectedOption = $(this).attr("id");
       // alert(selectedOption);
       
       tables.forEach(function(table) {
          table.hide();
       });
       
       $("#" + selectedOption + "-tbl").show();
    });
    
});