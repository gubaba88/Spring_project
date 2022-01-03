/*
 * 로그인 할때 쿠키관리를 위한 자바스크립트
 * 
 */
$("#save").change(function() {
	if ($(this).is(":checked")) {
		if (confirm("보안에 취약해질 위험이 있습니다. 정보를 저장하시겠습니까?")) {
		} else {
			$(this).prop('checked', false);
		}
	} else {
		$.cookie("log_id", "");
		$.cookie("log_pw", "");
	}
});
$("#login").submit(function() {
	if ($("#save").is(":checked")) {
		$.cookie("log_id", $("#memId").val());
		$.cookie("log_pw", $("#memPasswd").val());
	}
});
log_id = $.cookie("log_id");
log_pw = $.cookie("log_pw");
if (log_id && log_pw) {
	$("#memId").val(log_id);
	$("#memPasswd").val(log_pw);
	$("#save").prop("checked", true);
}
