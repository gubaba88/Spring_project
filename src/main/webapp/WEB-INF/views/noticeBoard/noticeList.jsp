<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 이름</title>
<link rel="stylesheet" href="./css/styles.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("#topic").change(function() {
		location.href="./NoticeList?postTopic="+$(this).val()
	})
})
function noticeInsert() {
	if (${sessionScope.memId == null}) {
		if (confirm("로그인이 필요합니다.\n로그인 하시겠습니까?")) {
			location.href="./Login"
		}
	} else {
		location.href="./NoticeInsert"
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
			<h1 class="display-4 fw-bolder"> 게임 정보 게시판 </h1>
			<p class="lead fw-normal text-white-50 mb-0">공지사항</p>
		</div>
	</div>
</header>
<section class="py-5">
<div class="container px-4 px-lg-5">
	<div class="my-2">
		<span>
			<select name="postTopic" id="topic">
				<option value="">전체
				<option value="공지" <c:if test="${param.postTopic == '공지'}">selected</c:if>>공지
				<option value="정보" <c:if test="${param.postTopic == '정보'}">selected</c:if>>정보
			</select>
		</span>
	</div>
	<table border="1" class="w-100 text-center type04">
		<thead class="bg-dark text-white">
			<tr>
				<th scope="col" width="8%"> No </th>
				<th scope="col" width="60%"> 제목 </th>
				<th scope="col" width="11%"> 글쓴이 </th>
				<th scope="col" width="21%"> 등록일 </th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty list}">
			<tr>
				<td colspan="4">글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="boardDTO" items="${list}">
			<tr>
				<td class="px-0">${boardDTO.postNumber}</td>
				<td class="px-2 text-start"><span class="">[${boardDTO.postTopic}] </span>
					<a href="./NoticeSelect?postNumber=${boardDTO.postNumber}">
						${boardDTO.postTitle}</a></td>
				<td class="px-0"> ${boardDTO.memId} </td>
				<c:choose>
					<c:when test="${fn:substring(boardDTO.postDate,0,10) == day}">
						<td>${fn:substring(boardDTO.postDate,11,19)}</td>
					</c:when>
					<c:otherwise>
						<td>${fn:substring(boardDTO.postDate,0,10)}</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
		
		</tbody>
	</table>
	<div class="text-end my-2">
		<c:choose>
			<c:when test="${sessionScope.memId == 'admin'}">
				<a class="btn btn-outline-dark mt-auto" onclick="noticeInsert()">글 쓰기</a>
			</c:when>
			<c:otherwise>
				<p align="center">공지사항은 'admin'계정만 작성할수 있습니다.</p>
			</c:otherwise>
		</c:choose>
	</div>
	<p align="center">
	<c:choose>
		<c:when test="${currentPage <= 1}">[이전]&nbsp;</c:when>
		<c:otherwise>
			<a href="./NoticeList?page=${currentPage-1}&postTopic=${param.postTopic}&keyfield=${param.keyfield}&keyword=${param.keyword}">[이전]</a>&nbsp;
		</c:otherwise>
	</c:choose> 
	<c:forEach var="page" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == page}">[${page}]</c:when>
			<c:otherwise>
				<a href="./NoticeList?page=${page}&postTopic=${param.postTopic}&keyfield=${param.keyfield}&keyword=${param.keyword}">[${page}]</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach> 
	<c:choose>
		<c:when test="${currentPage >= maxPage}">[다음]</c:when>
		<c:otherwise>
			<a href="./NoticeList?page=${currentPage+1}&postTopic=${param.postTopic}&keyfield=${param.keyfield}&keyword=${param.keyword}">[다음]</a>
		</c:otherwise>
	</c:choose>
	</p>
</div>
<div class="text-center">
	<form action="./NoticeList" method="get"  name="searchForm">
		<label for="keyfield"></label>
		<select name="keyfield" id="keyfield">
			<option value="all">전체 검색
			<option value="memId">작성자 검색
			<option value="postTitle">제목 검색
			<option value="postContent">내용 검색
		</select>
		<label for="keyword"></label>
		<input type="text" id="keyword" name="keyword" required placeholder= "검색어를 입력하세요.">
		<input type="hidden" id="postTopic" name="postTopic" value="${param.postTopic}">
		<button type="submit"> 검색 </button>
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