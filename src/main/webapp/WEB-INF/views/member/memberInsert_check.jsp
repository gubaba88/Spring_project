<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 확인</title>
<c:choose>
	<c:when test="${result}">
		<script type="text/javascript">
			alert("입력하신 아이디(${memId})로 가입 되었습니다.")
			if (${sessionScope.memId == 'admin'}) {
				location.href="./MemberList"
			} else {
				location.href="./BoardList"
			}
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			if (confirm("입력하신 아이디(${memId})가 이미 등록되어 있습니다.\n로그인 하시겠습니까?")) {
				location.href="./Login"
			} else {
				location.href="./MemberInsert"	
			}
	</script>
	</c:otherwise>
</c:choose>
</head>
<body>

</body>
</html>