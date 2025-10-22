/**
 * 회원 리스트 탭 제어
 */
$().ready(function() {
    
    var tables = [$('#all_user_tbl'), $('#blogger_tbl'), $('#adtr_tbl')];
    
    $('table').hide();
    $('#all_user_tbl').show();
    
    $('input[name="admin_user_tab"').on('change', function() {
       
       var selectedOption = $(this).attr("id");
       // alert(selectedOption);
       
       tables.forEach(function(table) {
          table.hide();
       });
       
       $('#' + selectedOption + '_tbl').show();
    });
    
});