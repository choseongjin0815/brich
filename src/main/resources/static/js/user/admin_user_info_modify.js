/**
 * 
 */
$().ready(function() {
    
    var MAX_CHECK_COUNT = 3;
    
    $(".category-checkbox").on("change", function() {
        
        var checkedCount = $(".category-checkbox:checked").length;
        
        if(checkedCount > MAX_CHECK_COUNT) {
            $(this).prop("checked", false);
            
            alert("카테고리는 최대 " + MAX_CHECK_COUNT + "개까지 선택 가능합니다.");
        }
        
    });
    
});