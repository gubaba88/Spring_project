/**
 * 
 */
$(function( ) {
	$("#memJoin").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.parents("p") );
		},
		rules : {
			memId : {
				required : true,
				spaceCk : true,
				minlength : 4,
				maxlength : 12
			},
			memPasswd : {
				required : true,
				spaceCk : true,
				minlength : 8,
				maxlength : 15,
				passwordCk : true
			},
			passwdRe : {
				required : true,
				equalTo : "#memPasswd"	
			},
			memEmail : {
				required : true,
				email : true,
				maxlength: 25
			},
			memName : {
				required : true,
				spaceCk : true,
				maxlength: 4
			},
			memPhone : {
				required : true,
				digits : true,
				maxlength: 15
			},
			memBirth : {
				required : true,
				birthCk : true,
				maxlength: 6
			},
			memGender : "required",
			memAddress : {
				maxlength: 30
			},
			memComment : {
				maxlength: 30
			},
			agree : "required"
		},
		messages : {
			memId : {
				required : " 아이디를 공백없이 입력하세요.",
				spaceCk : " 아이디를 공백 없이 입력하세요.",
				minlength : " 최소 {4} 글자입니다.",
				maxlength : " 최대 {12} 글자입니다."
			},
			memPasswd : {
				required : " 비밀번호를 공백없이 입력하세요.",
				spaceCk : " 비밀번호를 공백없이 입력하세요. ",
				minlength : " 비밀번호는 최소 {8} 글자입니다.",
				maxlength : " 비밀번호는 최대 {15} 글자입니다.",
				passwordCk : " 비밀번호는 영문, 숫자, 특수문자 조합이어야 합니다."
			},
			passwdRe : {
				required : " 비밀번호 확인을 입력하세요.",
				equalTo : " 비밀번호를 확인하세요"
			},
			memEmail: {
				required : " 이메일을  공백없이 입력하세요.",
				email : " 이메일의 형식을 확인하세요. 예) admin@naver.com",
				maxlength :" 이메일은 최대 {25}글자 입니다." 
			},
			memName: {
				required : " 이름을 공백없이 입력하세요.",
				spaceCk : " 이름을 공백없이 입력하세요. ",
				maxlength : " 이름은 최대 {4}글자 입니다." 
			},
			memPhone : {
				required : " 전화번호를 공백없이 숫자로만 입력하세요.",
				digits : " 전화번호는 공백없이 숫자로만 입력하세요.",
				maxlength : " 전화번호는 최대 {15}자리 입니다." 
			},
			memBirth : {
				required : " 주민번호 앞자리 형식에 맞게 입력하세요. 예)991231",
				birthCk : " 주민번호 앞자리 형식에 맞게 입력하세요. 예)991231",
				maxlength : " 주민번호 앞자리 숫자 공백없이 {6}자리 입니다." 
			},
			memGender : " 주민번호 뒷자리 첫{1}자리를 선택하세요.",
			memAddress : {
				maxlength: " 주소 길이 최대 {30}글자 입니다."
			},
			memComment : {
				maxlength: " 인사말은 최대 {30}글자 입니다."
			},
			agree : " 약관 동의가 필요합니다."
		}
	});
	$("#memUp").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.parents("p") );
		},
		rules : {
			memPasswd : {
				required : true,
				spaceCk : true,
				minlength : 8,
				maxlength : 15,
				passwordCk : true
			},
			passwdRe : {
				required : true,
				equalTo : "#memPasswd"	
			},
			memEmail : {
				required : true,
				email : true,
				maxlength: 25
			},
			memPhone : {
				required : true,
				digits : true,
				maxlength: 15
			},
			memAddress : {
				maxlength: 30
			},
			memComment : {
				maxlength: 30
			},
			agree : "required"
		},
		messages : {
			memPasswd : {
				required : " 비밀번호를 공백없이 입력하세요.",
				spaceCk : " 비밀번호를 공백없이 입력하세요. ",
				minlength : " 비밀번호는 최소 {8} 글자입니다.",
				maxlength : " 비밀번호는 최대 {15} 글자입니다.",
				passwordCk : " 비밀번호는 영문, 숫자, 특수문자 조합이어야 합니다."
			},
			passwdRe : {
				required : " 비밀번호 확인을 입력하세요.",
				equalTo : " 비밀번호를 확인하세요"
			},
			memEmail: {
				required : " 이메일을  공백없이 입력하세요.",
				email : " 이메일의 형식을 확인하세요. 예) admin@naver.com",
				maxlength :" 이메일은 최대 {25}글자 입니다." 
			},
			memPhone : {
				required : " 전화번호를 공백없이 숫자로만 입력하세요.",
				digits : " 전화번호는 공백없이 숫자로만 입력하세요.",
				maxlength : " 전화번호는 최대 {15}자리 입니다." 
			},
			memAddress : {
				maxlength: " 주소 길이 최대 {30}글자 입니다."
			},
			memComment : {
				maxlength: " 인사말은 최대 {30}글자 입니다."
			},
			agree : " 약관 동의가 필요합니다."
		}
	});
	$.validator.addMethod("passwordCk",  function( value, element ) {
		return this.optional(element) ||  /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(value);
	});
	$.validator.addMethod("spaceCk",  function( value, element ) {
		return this.optional(element) ||  value.indexOf(" ")==-1;
	});
	$.validator.addMethod("birthCk",  function( value, element ) {
		return this.optional(element) ||  /\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])/.test(value);
	});
	$("#login").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.next("p") );
		},
		rules : {
			memId : "required",
			memPasswd : "required"
		},
		messages : {
			memId : "아이디를 입력하세요.",
			memPasswd : "비밀번호를 입력하세요."
		}
	});
	$("#inPost").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.parent("div").next("p") );
		},
		rules : {
			topic : "required",
			postTitle : {
				required : true,
				maxlength : 20
			},
			postContent : {
				required : true,
				maxlength : 300
			}
		},
		messages : {
			topic : "글 주제를 선택하세요.",
			postTitle : {
				required : "글 제목를 입력하세요.",
				maxlength : "글 제목은 최대 {20}글자입니다."
			},
			postContent : {
				required : "내용를 입력하세요.",
				maxlength : "글 내용은 최대 {300}글자입니다."
			}
		}
	});
	
	$("#comment_form").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.parent("div").next("p") );
		},
		rules : {
			comment_name : {
				required : true,
				spaceCk : true,
				maxlength: 12
			},
			comment_content : {
				required : true,
				maxlength: 30
			}
		},
		messages : {
			comment_name : {
				required : "댓글 작성자 명을 공백없이 입력하세요. ",
				spaceCk : "댓글 작성자 명을 공백없이 입력하세요. ",
				maxlength: "작성자 명은 최대 {12}글자입니다."
			},
			comment_content : {
				required : "댓글 내용을 입력하세요. ",
				maxlength: "댓글 내용은 최대 {30}글자입니다."
			}
		}
	});

});