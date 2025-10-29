$().ready(function() {
    var category = $("input[name='category']");
    var sortBy = $("select[name='sortBy']");
    var searchKeyword = $("input[name='searchKeyword']");
    var searchButton = $(".search-button");
    var campaignFav = $(".campaign-fav");
    var hereCategory = new URLSearchParams(window.location.search).get('category') || '';
    var campaignMainBlock = $(".campaign-main-block");
    var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/');    
    
    if (here.includes('campaignmain')) {
      $('.campaign-status').removeClass('display-none');
    }
    if (here.includes('closedcampaign')) {
      $('.campaign-fav').addClass('display-none');
    }
    
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
    campaignFav.on("click", function(e) {
        e.stopPropagation();
        var cmpnId = $(this).data("cmpn-id");
        window.location.href = "/blgr/love/"+ cmpnId ;
    })
    campaignMainBlock.on("click", function() {
        var cmpnId = $(this).data("cmpn-id");
        window.location.href = "/campaigndetail/"+ cmpnId ;
    })
});
