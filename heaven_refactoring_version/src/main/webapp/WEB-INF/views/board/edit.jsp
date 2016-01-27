<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Edit</title>
</head>
<body>
	<form:form commandName="boardVO" method="POST">
		<table border="1">
			<tr>
				<th><label for="password">비밀번호</label></th>
				<td><input type="password" id="pwd" name="pwd" /> ${msg}</td>
			</tr>
		</table>
		<dir>
			<input type="submit" value="수정">
			<a href="<c:url value="/board/list" />">목록</a>
		</dir>
	</form:form>
</body>
</html>