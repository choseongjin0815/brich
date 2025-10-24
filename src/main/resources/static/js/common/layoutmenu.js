$(document).ready(function(){
  
  $('#main_menu > li > a').click(function(){
    $(this).next($('.snd_menu')).slideToggle('fast');
  })
  $('.snd_menu > li > a').click(function(e){
    e.stopPropagation();
    $(this).next($('.trd_menu')).slideToggle('fast');
  })
  
  $(".my-campaign").click(function() {
    $(this).find($(".sub-menu")).slideToggle('fast');
  })

  
})