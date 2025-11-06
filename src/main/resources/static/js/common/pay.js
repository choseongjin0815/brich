$().ready(function() {
		
	const onlyDigits = /[0-9]/;
	const allowKeys = new Set([
	  'Backspace','Delete','ArrowLeft','ArrowRight','Home','End','Tab'
	]);

	const buyDays = $('#buyDays');

	function sanitize(){
	  buyDays.val((buyDays.val() || '').replace(/\D+/g, '')); 
	}

	buyDays.on('keydown', function(e){
	  if (allowKeys.has(e.key)) {
		return;
	}
	  if (!onlyDigits.test(e.key)) {
	    e.preventDefault();
	  }
	});
	
	buyDays.on('input', sanitize);
	
	
///////
	const start 	= $('#startDate');
	const days  	= $('#buyDays');
	const end       = $('#endDate');
	const cmpnStart = $('#campStartDate');
	const campEnd   = $('#pstEndDt');
	// Date 형태 생성
	function parseDate(yyyyMMdd) {
		if (!yyyyMMdd) {
			return null
		};
		const [y, m, d] = yyyyMMdd.split('-').map(Number);
		if (!y || !m || !d){
			return null;
		} 
		return new Date(y, m - 1, d);
	}
	
	// Date → YYYY-MM-DD
	function formatDate(dt) {
	  const y = dt.getFullYear();
	  const m = String(dt.getMonth() + 1).padStart(2, '0');
	  const d = String(dt.getDate()).padStart(2, '0');
	  return `${y}-${m}-${d}`;
	}
	
	// dt + n일
	function addDays(dt, n) {
	  const copy = new Date(dt);
	  copy.setDate(copy.getDate() + n);
	  return copy;
	}
	
	function recalc() {
	  const startVal = start.val();
	  const daysStr  = (days.val() || '').trim();
	  const startDate = parseDate(startVal);
	  const daysNum  = Number(daysStr);
	  
	  if (!startDate || !daysStr || !Number.isInteger(daysNum) || daysNum <= 0) {
	    end.val('');
		campEnd.attr('min','');
	    return;
	  }
	  const endDate = addDays(startDate, daysNum);
	  const cmpnStartDate = addDays(endDate, 3);
	  end.val(formatDate(endDate));
	  cmpnStart.val(formatDate(cmpnStartDate));	
	  const campStartStr = formatDate(cmpnStartDate);
	  campEnd.attr('min', campStartStr);
	}
	
	start.on('change input', recalc);
	days.on('input', function(){
	  recalc();
	});
	
	$(document).on('input', '#buyDays', function () {
	  $('#payDay').text($(this).val());
	  totalPay = ($(this).val()*$('.payPrice').data('dayprice'))
	  				+ ($('.payPrice').data('rcrtprsnn')*Number($('.payPerson').data('person'))); 
	  $('.total-pay').text(totalPay);
	});
	
	// 초기 계산(페이지에 값이 미리 채워져 있을 경우 대비)
	recalc();
	

	
	
});