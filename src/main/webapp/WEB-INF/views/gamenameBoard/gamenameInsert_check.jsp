<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 결과</title>
<c:choose>
	<c:when test="${result}">
		<script type="text/javascript">
			alert("게시판에 글 - ${boardDTO.postTitle}을 등록하였습니다.")
			location.href="./GamenameSelect?postNumber=${boardDTO.postNumber}"
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			if (confirm("오류가 발생했습니다.\n다시 작성 하시겠습니까?")) {
				location.href="./GamenameInsert"
			} else {
				location.href="./GamenameList"	
			}
	</script>
	</c:otherwise>
</c:choose>
</head>
<body>

</body>
</html> 