<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>글 수정</title>
<link rel="stylesheet" type="text/css" href="./css/styles.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/validity.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$('input:radio[name="postTopic"]:radio[value="${postDTO.postTopic}"]').attr('checked', true);	
});
</script>
</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="./BoardList"> 메인 화면 </a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="nav-item"><a class="nav-link" href="./NoticeList">공지사항</a></li>
				<c:choose>
					<c:when test="${empty sessionScope.memId}">
						<li class="nav-item"><a class="nav-link" id="mem_In" href="./MemberInsert">회원 가입</a></li>								
					</c:when>
					<c:when test="${sessionScope.memId == 'admin'}">
						<li class="nav-item"><a class='nav-link' href='./MemberList'>회원 목록</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class='nav-link' href='./MemberSelect?memId=${sessionScope.memId}'>회원 정보</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<span>
			<c:if test="${!empty sessionScope.memId}">
				${sessionScope.memId} 님
			</c:if>
			<c:choose>
				<c:when test="${empty sessionScope.memId}">
					<a id="login_btn" class="btn btn-outline-dark mt-auto" href="./Login">로그인</a>
				</c:when>
				<c:otherwise>
					<a id='logOut_btn' class='btn btn-outline-dark mt-auto'>로그 아웃</a>
				</c:otherwise>
			</c:choose>
			</span>
		</div>
	</div>
</nav>
<!-- Header-->
<header class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-white">
			<h1 class="display-4 fw-bolder"> 글 수정 </h1>
			<p class="lead fw-normal text-white-50 mb-0">
				<a href="./GamenameList">게시판 이름</a></p>
		</div>
	</div>
</header>

<section class="py-5">
<div class="container px-4 px-lg-5">
	<div class="viewBox">
		<form name="upPost" id="upPost" action="./GamenameUpdate" method="post" enctype="multipart/form-data">
			<div>
				<label for="memId"></label><input type="text" name="memId" id="memId" class="postIn"
					value="${boardDTO.memId}" readonly>
				<label for="postTopic">&nbsp; 글 주제 : </label>
				<c:if test="${sessionScope.memId == 'admin'}">
				<input type="radio" id="postTopic" name="postTopic" value="공지" <c:if test="${boardDTO.postTopic == '공지'}">checked</c:if>><span>공지 &nbsp;</span>
				</c:if>				
				<input type="radio" id="postTopic" name="postTopic" value="정보" <c:if test="${boardDTO.postTopic == '정보'}">checked</c:if>>정보 &nbsp;
				<input type="radio" id="postTopic" name="postTopic" value="스포" <c:if test="${boardDTO.postTopic == '스포'}">checked</c:if>>스포 &nbsp;
				<input type="radio" id="postTopic" name="postTopic" value="질문" <c:if test="${boardDTO.postTopic == '질문'}">checked</c:if>>질문 &nbsp;
				<input type="radio" id="postTopic" name="postTopic" value="잡담" <c:if test="${boardDTO.postTopic == '잡담'}">checked</c:if>>잡담
				<label for="postNumber"></label><input type="hidden" name="postNumber" id="postNumber" class="postIn"
					value="${boardDTO.postNumber}" readonly>
			</div>
			<p class="m-0"></p>
			<div>
				<label for="postTitle"></label><input type="text" name="postTitle" id="postTitle" class="insertTitle" 
					value="${boardDTO.postTitle}">
			</div>
			<p class="m-0"></p>
			<div class="text-start">
				<label for="postContent"></label><textarea class="textbox" cols="10" rows="8" name="postContent" id="postContent">${boardDTO.postContent}</textarea>
			</div>
			<p class="m-0"></p>
			<div>
				<label for="fileName">파일 수정 : </label>${boardDTO.fileName}
				<input type="hidden" name="fileName" id="fileName" value="${boardDTO.fileName}">
				<br/>
				<label for="attachedFile"></label><input type="file" name="attachedFile" id="attachedFile">
			</div>
			<p class="m-0"></p>
			<div style="text-align: right;margin-top: 6px;">
				<button type="submit" class="btn btn-outline-dark mt-auto">글 수정</button>
				<button type="button" class="btn btn-outline-dark mt-auto" onclick="history.back();">취 소</button>
			</div>
		</form>
	</div>
</div>
</section>
<!-- Footer-->
<footer class="py-5 bg-dark">
	<div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="./js/scripts.js"></script>
</body>
</html>