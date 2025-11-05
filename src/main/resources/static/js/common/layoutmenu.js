
$(document).ready(function(){
  var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/');
  console.log(here[0]);
  if (here.includes('submittedmycampaign') 
        || here.includes('campaignongoing')
        || here.includes('closedcampaign')
        || here.includes('favcampaign')) {
    $(".my-sub-menu").removeClass('display-none');
  }
  
  $(".my-campaign").click(function() {
    $(this).find($(".my-sub-menu")).slideToggle('fast');
    $('.caret-up').toggleClass('display-none');
    $('.caret-down').toggleClass('display-none');
  })
  
  $('common-menu').removeClass('menu-selected');
  
  if (here.includes('campaignmain') || here.includes('campaigndetail')) {
    $('.menu-campaignmain-selected').addClass('menu-selected')
  }
  if ( here.includes('submittedmycampaign')) {
      $('.menu-my-submitted-campaign-selected').addClass('menu-selected')
  }
  if ( here.includes('campaignongoing')) {
      $('.menu-my-ongoing-campaign-selected').addClass('menu-selected')
  }
  if ( here.includes('closedcampaign')) {
      $('.menu-my-closed-campaign-selected').addClass('menu-selected')
  }
  if ( here.includes('favcampaign')) {
      $('.menu-my-fav-campaign-selected').addClass('menu-selected')
  }
  
  if ( here.includes("chat")) {
    $('.menu-message-selected').addClass('menu-selected');
  }
  
  if ( here.includes("help")) {
      $('.menu-help-selected').addClass('menu-selected');
    }
    
  if ( here.includes("account")) {
      $('.menu-account-selected').addClass('menu-selected');
  }
  
  if ( here[0].includes('report')) {
      $('.menu-report-selected').addClass('menu-selected');
  }
})