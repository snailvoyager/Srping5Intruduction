<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
</head>
<body>
	<p>ID : ${member.id}</p>
	<p>Email : ${member.email }</p>
	<p>Name : ${member.name }</p>
	<p>Reg Date : <tf:formatDateTime value="${member.registerDateTime }" pattern="yyyy-MM-dd HH:mm"/></p>
</body>
</html>