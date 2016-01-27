<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete</title>
</head>
<body>
	<form:form commandName="boardVO" method="post">
		<form:input path="seq" />
		 비밀번호<input name="pwd" />
		<input type="submit">
		<a href="<c:url value="/board/read/${boardVO.seq}" />">취소</a>
	</form:form>
	<div>${msg}</div>

	<%-- Refactoring 전 코드
	 <form name="deleteForm" action="<c:url value="/board/delete" />"
		method="post">
		<input name="seq" value="${seq}" /> 비밀번호<input name="pwd" /> <input
			type="submit"> <a href="<c:url value="/board/read/${seq}" />">취소</a>
	</form>
	<div>${msg}</div> --%>
</body>
</html>