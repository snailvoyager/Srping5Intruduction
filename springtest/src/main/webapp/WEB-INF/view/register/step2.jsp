<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>
<body>
	<h2>회원 정보 입력</h2>
	<form action="step3" method="post">
	<p>
		<label>email<br>
		<input type="text" name="email" id="email">
		</label>
	</p>
	<p>
		<label>Name<br>
		<input type="text" name="name" id="name">
		</label>
	</p>
	<p>
		<label>Password<br>
		<input type="password" name="password" id="password">
		</label>
	</p>
	<p>
		<label>Confirm Password<br>
		<input type="password" name="confirmPassword" id="confirmPassword">
		</label>
	</p>
	<input type="submit" value="Complete">
	</form>
</body>
</html>