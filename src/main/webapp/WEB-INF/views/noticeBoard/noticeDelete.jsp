<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 결과</title>
</head>
<body>
<script type="text/javascript">
alert("${boardDTO.postNumber}번 글 - ${boardDTO.postTitle}\n삭제가 완료되었습니다.")
location.href="./NoticeList";
</script>
</body> 
</html> 