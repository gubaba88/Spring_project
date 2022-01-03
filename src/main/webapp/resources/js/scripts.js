/*!
* Start Bootstrap - Shop Homepage v5.0.4 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
$("#logOut_btn").click(function() {
	if(confirm("로그아웃 하시겠습니까?")) {
		location.href="./Logout.do"
	}
})
$("#comment_form").submit(function() {
	if ($("#comment_name").val() && $("#comment_content").val()) {
		date = new Date();
		yy = date.getFullYear();
		mm = date.getMonth() + 1;
		dd = date.getDate();
		hh = date.getHours();
		mi = date.getMinutes();
		ss = date.getSeconds();
		today = yy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + ss;
		new_li = $("<li>");
		new_li.addClass("comment_item");
		writer_p = $("<p>");
		writer_p.addClass("writer");
		name_span = $("<span>");
		name_span.addClass("name");
		name_span.html($("#comment_name").val() + "님");
		date_span = $("<span>");
		date_span.html(" / " + today + " ");
		del_input = $("<input>");
		del_input.prop({
			"type" : "button",
			"value" : "X",
			"class" : "btn btn-outline-dark p-2 py-0",
			"style" : " "
		});
		del_input.addClass("delete_btn");
		content_p = $("<p>");
		content_p.html($("#comment_content").val());
		writer_p.append(name_span).append(date_span).append(del_input);
		new_li.append(writer_p).append(content_p);
		$("#comment_list").append(new_li);
		$("#comment_name").val("");
		$("#comment_content").val("");
		return false;
	}
});
$(document).on("click", ".delete_btn", function() {
	if (confirm("선택하신 댓글을 삭제하시겠습니까?")) {
		$(this).parents(".comment_item").remove();
	}
});
$("#fileName").change(function() {
	if (this.files && this.files[0]) {

		var maxSize = 10 * 1024 * 1024;
		var fileSize = this.files[0].size;

		if(fileSize > maxSize){
			alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
			$(this).val('');
			return false;
		}
	}
})

