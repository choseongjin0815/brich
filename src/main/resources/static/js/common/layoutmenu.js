$(document).ready(function(){
  var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/')[0];
  if(here === 'submittedmycampaign'){
     $(".my-sub-menu").removeClass('display-none');
  }
  
  $(".my-campaign").click(function() {
    $(this).find($(".my-sub-menu")).slideToggle('fast');
    $('.caret-up').toggleClass('display-none');
    $('.caret-down').toggleClass('display-none');
  })
  
  $('common-menu').removeClass('menu-selected');
  
  if (here === 'campaignmain' || here === 'campaigndetail') {
    $('.menu-campaignmain-selected').addClass('menu-selected')
  }
  if (here === 'submittedmycampaign') {
      $('.menu-my-submitted-campaign-selected').addClass('menu-selected')
  }
})