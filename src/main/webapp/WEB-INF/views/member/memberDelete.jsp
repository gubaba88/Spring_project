<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${sessionScope.memId == 'admin'}">
		<script type="text/javascript">
			alert("회원 (${param.memId})의 탈퇴가 완료되었습니다.")
			location.href="./MemberList"
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("탈퇴가 완료되었습니다.")
			location.href="./Logout";
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>