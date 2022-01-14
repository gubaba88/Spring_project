<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/styles.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/validity.js" type="text/javascript"></script>
<script src="./js/jquery.cookie.js" type="text/javascript"></script>
<script src="./js/login.js" type="text/javascript"></script>
</head>
<body>
<!-- Header-->
<header class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-white">
			<h1 class="display-6 fw-bolder"> LOGIN </h1>
		</div>
	</div>
</header>

<section class="py-3">
	<div class="m-auto text-center">
		<form name="login" id="login" method="post" action="./Login" enctype="application/x-www-form-urlencoded">
			<label for="memId"></label><input type="text" name="memId" id="memId" class="loginInput" maxlength="15" autofocus
				placeholder="아이디 : String memId -> varchar2(12) memId">
			<p class="m-0"></p>
			<label for="memPasswd"></label><input type="password" name="memPasswd" id="memPasswd" class="loginInput" maxlength="17"
				placeholder="비밀번호 : Sting memPasswd -> varchar2(15) memPasswd">
			<p class="m-0"></p>
			<button type="submit" class="btn btn-outline-dark mt-1 loginInput">로그인</button>
			<div class="loginInput mx-auto mt-1">
				<span class="btnd" style="margin-right: 147px;">
					<label for="save"></label><input type="checkbox" name="save" id="save"> 아이디 저장
				</span>
				<span class="text-end">
					<a type="button" class="btn btn-outline-dark" href='./MemberInsert'> 회원 가입 </a>
				</span>
			</div>
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
<script src="./js/login.js"></script>
</body>
</html>