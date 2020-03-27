<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>
<body>
	<h2>회원 정보 입력</h2>
	<form:form action="step3" modelAttribute="formData">
	<p>
		<label>email<br>
		<form:input path="email"/>
		</label>
	</p>
	<p>
		<label>Name<br>
		<form:input path="name"/>
		</label>
	</p>
	<p>
		<label>Password<br>
		<form:password path="password"/>
		</label>
	</p>
	<p>
		<label>Confirm Password<br>
		<form:password path="confirmPassword"/>
		</label>
	</p>
	<input type="submit" value="Complete">
	</form:form>
</body>
</html>