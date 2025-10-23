$().ready(function() {
    var category = $("input[name='category']");
    var sortBy = $("select[name='sortBy']");
    var searchKeyword = $("input[name='searchKeyword']");
    var searchButton = $(".search-button");

	    category.on("change", function() {
	        var searchParam = $(".search_section").serialize();
	        searchParam = "?" + searchParam ;
	        window.location.href = window.location.pathname + searchParam;
	    });
	    sortBy.on("change", function() {
	        var searchParam = $(".search_section").serialize();
	        searchParam = "?" + searchParam ;
	        window.location.href = window.location.pathname + searchParam;
	    });
	    searchKeyword.on("keyup", function(event) {
	        if (event.keyCode === 13) {
	            var searchParam = $(".search_section").serialize();
	            searchParam = "?" + searchParam ;
	            window.location.href = window.location.pathname + searchParam;
	        }
	    });
	    searchButton.on("click", function() {
	        var searchParam = $(".search_section").serialize();
	        searchParam = "?" + searchParam 
	        window.location.href = window.location.pathname + searchParam;
	    });
});
