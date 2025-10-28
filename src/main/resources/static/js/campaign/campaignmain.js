$().ready(function() {
    var category = $("input[name='category']");
    var sortBy = $("select[name='sortBy']");
    var searchKeyword = $("input[name='searchKeyword']");
    var searchButton = $(".search-button");
    var hereCategory = new URLSearchParams(window.location.search).get('category') || '';
    $('.category-seleted-box').each(function () {
      if($(this).data('category-menu') === hereCategory){
         $(".category-seleted-box-"+hereCategory).removeClass('visibility-hidden');
       }
    });
    
    category.on("change", function() {
        var searchParam = $(".search-section").serialize();
        searchParam = "?" + searchParam ;
        window.location.href = window.location.pathname + searchParam;
    });
    sortBy.on("change", function() {
        var searchParam = $(".search-section").serialize();
        searchParam = "?" + searchParam ;
        window.location.href = window.location.pathname + searchParam;
    });
    searchKeyword.on("keyup", function(event) {
        if (event.keyCode === 13) {
            var searchParam = $(".search-section").serialize();
            searchParam = "?" + searchParam ;
            window.location.href = window.location.pathname + searchParam;
        }
    });
    searchButton.on("click", function() {
        var searchParam = $(".search-section").serialize();
        searchParam = "?" + searchParam 
        window.location.href = window.location.pathname + searchParam;
    });
});