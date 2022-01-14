<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>	<link rel="stylesheet" href="./css/styles.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/validity.js" type="text/javascript"></script>
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
			<h1 class="display-4 fw-bolder"> 글 작성 </h1>
			<p class="lead fw-normal text-white-50 mb-0">
				<a href="./GamenameList">게시판 이름</a></p>
		</div>
	</div>
</header>

<section class="py-5">
<div class="container px-4 px-lg-5">
	<div class="viewBox">
		<form name="inPost" id="inPost" action="./GamenameInsert" method="post" enctype="multipart/form-data">
			<fieldset>
			<label for="gameNumber"></label><input type="hidden" name="gameNumber" id="gameNumber" value="${boardDTO.gameNumber}">
			<div>
				<label for="memId"></label><input type="text" name="memId" id="memId" class="postIn" value="${sessionScope.memId}" readonly>
				<label for="postTopic">&nbsp; 글 주제 : </label>
				<c:if test="${sessionScope.memId == 'admin'}">
				<input type="radio" id="postTopic" name="postTopic" value="공지"><span>공지 &nbsp;</span>
				</c:if>
				<input type="radio" id="postTopic" name="postTopic" value="정보"><span>정보 &nbsp;</span>
				<input type="radio" id="postTopic" name="postTopic" value="스포"><span>스포 &nbsp;</span>
				<input type="radio" id="postTopic" name="postTopic" value="질문"><span>질문 &nbsp;</span>
				<input type="radio" id="postTopic" name="postTopic" value="잡담" checked><span>잡담</span>
			</div>
			<p class="m-0"></p>
			<div>
				<label for="postTitle"></label><input type="text" name="postTitle" id="postTitle" class="insertTitle" placeholder=" 글 제목 : String postTitle -> varchar2(60) postTitle">
			</div>
			<p class="m-0"></p>
			<div class="text-start">
				<label for="postContent"></label><textarea class="textbox" cols="10" rows="8" name="postContent" id="postContent" placeholder=" 글 내용. String postContent -> Varchar2(900) postContet"></textarea>
			</div>
			<p class="m-0"></p>
			<div>
				<label for="attachedFile">파일 첨부 : </label>
				<input type="file" name="attachedFile" id="attachedFile" placeholder="파일 첨부">
			</div>
			<p class="m-0"></p>
			<div style="text-align: right;margin-top: 6px;">
				<button type="submit" class="btn btn-outline-dark mt-auto">글 작성</button>
				<button type="button" class="btn btn-outline-dark mt-auto" onclick="history.back();">취 소</button>
			</div>
		</fieldset>
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