$().ready(function() {
		
	const onlyDigits = /[0-9]/;
	const allowKeys = new Set([
	  'Backspace','Delete','ArrowLeft','ArrowRight','Home','End','Tab'
	]);
	
	const el = document.getElementById('day');
	
	let composing = false;
	el.addEventListener('compositionstart', () => composing = true);
	el.addEventListener('compositionend', () => { composing = false; sanitize(); });
	
	el.addEventListener('keydown', (e) => {
	  if (composing) return;
	  if (allowKeys.has(e.key)) return;
	  if (e.ctrlKey || e.metaKey) return; // Ctrl+C/V/X/A 허용
	  if (!onlyDigits.test(e.key)) e.preventDefault(); // 숫자 외 금지
	});
	
	el.addEventListener('paste', (e) => {
	  e.preventDefault();
	  const text = (e.clipboardData || window.clipboardData).getData('text');
	  const digits = text.replace(/\D+/g,'');
	  document.execCommand('insertText', false, digits);
	});
	
	el.addEventListener('input', sanitize);
	function sanitize() {
	  el.value = el.value.replace(/\D+/g,''); // 안전망: 숫자 외 제거
	}
});