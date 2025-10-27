$(document).ready(function(){
	var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/')[0];

  $(".my-campaign").click(function() {
    $(this).find($(".my-sub-menu")).slideToggle('fast');
    $('.caret-up').toggleClass('display-none');
    $('.caret-down').toggleClass('display-none');
  })
	
  if (here === 'campaignmain' || 'campaigndetail') {
    $('.menu-campaignmain-selected').addClass('menu-selected')
  }
})