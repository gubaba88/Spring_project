<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 확인</title>
	<link rel="stylesheet" href="./css/styles.css">
	<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	function memDelete() {
		if (confirm("회원 (${memberDTO.memId}) 탈퇴 하시겠습니까?")) {
			location.href="./MemberDelete?memId=${memberDTO.memId}"
		}
	}
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
			<h1 class="display-6 fw-bolder"> 회원 정보 확인 </h1>
			<p class="lead fw-normal text-white-50 mb-0">
				<c:if test="${sessionScope.memId == 'admin'}">
					<a href="./MemberList">회원 목록</a>
				</c:if>
			</p>
		</div>
	</div>
</header>

<section class="py-5">
<div class="container">
	<table border="1" class="w-50 text-center type04 m-auto">
		<tbody class="m-0">
			<tr>
				<td class="bg-dark text-white">회원 번호</td><td>${memberDTO.memNumber}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">아이디</td><td>${memberDTO.memId}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">이메일</td><td>${memberDTO.memEmail}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">이름</td><td>${memberDTO.memName}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">전화번호</td><td>${memberDTO.memPhone}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">생일</td><td>${memberDTO.memBirth}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">성별</td>
				<c:choose>
					<c:when test="${memberDTO.memGender == 'M'}">
						<td>남성</td>
					</c:when>
					<c:otherwise>
						<td>여성</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td class="bg-dark text-white">주소</td><td>${memberDTO.memAddress}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">인사말</td><td>${memberDTO.memComment}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">포인트</td><td>${memberDTO.memPoint}</td>
			</tr>
			<tr>
				<td class="bg-dark text-white">가입일</td><td>${memberDTO.memDate}</td>
			</tr>
		</tbody>
	</table>
	
	<div class="text-end mt-2 mx-auto w-50">
		<c:if test="${sessionScope.memId == 'admin' || sessionScope.memId == memberDTO.memId}">
			<input type="button" class="btn btn-outline-dark mt-auto" value="정보 수정" onClick="location.href='./MemberUpdate?memId=${memberDTO.memId}'">
			<input type="button" class="btn btn-outline-dark mt-auto" value="회원 탈퇴" onClick="memDelete()">
		</c:if>
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