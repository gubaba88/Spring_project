<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="./css/styles.css">
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
			<h1 class="display-4 fw-bolder">회원 가입</h1>
			<p class="lead fw-normal text-white-50 mb-0">잘못된 입력으로 인한 피해는 책임지지 않습니다.</p>
		</div>
	</div>
</header>
<!-- Section-->
<section class="py-5">
	<div class="container px-4 px-lg-5 text-start">
		<form  id="memJoin" action="./MemberInsert" method="post" enctype="application/x-www-form-urlencoded">
		<fieldset>
			<p><label for="memId">아 이 디*</label>
			<input type="text" name="memId" id="memId" class="memInP"
				placeholder="String memId -> Varchar2(12) memId" autofocus></p>
			<p><label for="memPasswd">비밀번호*</label>
			<input type="password"  name="memPasswd" id="memPasswd" class="memInP"
				placeholder="String memPasswd -> Varchar2(15) memPasswd"></p>
			<p><label for="passwdRe">비밀번호 확인</label>
			<input type="password" name="passwdRe" id="passwdRe" class="memInP"
				placeholder="String memPasswd -> Varchar2(15) memPasswd"></p>				
			<p><label for="memEmail">이 메 일*</label>
			<input type="email" name="memEmail" id="memEmail" class="memInP"
				placeholder="String memEmail -> Varchar2(25) memEmail"></p>
			<p><label for="memName">이&nbsp;&nbsp;&nbsp;&nbsp;름*</label>
			<input type="text"  name="memName" id="memName" class="memInP" 
				placeholder="String memName -> Varchar2(12) memName"></p>
			<p><label for="memPhone">전화번호*</label>
			<input type="text"  name="memPhone" id="memPhone" class="memInP" 
				placeholder="String memPhone -> Varchar2(15) memPhone"></p>
			<p><label for="memBirth">주민번호*</label>
			<input type="text"  name="memBirth" id="memBirth" class="memInBirth" 
				placeholder="String memBirth -> Date memBirth"> - 
			<select name="memGender" class="memInGender">
				<option value="1">1
				<option value="2">2
				<option value="3">3
				<option value="4">4
			</select>******</p>
			<p><label for="memAddress">주&nbsp;&nbsp;&nbsp;&nbsp;소</label>
			<input type="text" name="memAddress" id="memAddress" class="memInP" 
				placeholder="String memAddress -> Varchar2(90) memAdress"></p>
			<p><label for="memComment">인 사 말</label>
			<input type="text"  name="memComment" id="memComment" class="memInP" 
				placeholder="String memComment -> Varchar2(90) memComment"></p>
			<p><label for="agree"> 약관 동의 * </label>
			<label> 동의합니다. <input type="checkbox" name="agree" id="agree"></label></p>
			<button type="button" class="btn btn-outline-dark mt-auto memInCancel" onclick="history.back()">돌아가기</button>
			<button type="submit" class="btn btn-outline-dark mt-auto memInSubmit">회원 가입</button>
		</fieldset>
		</form>
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