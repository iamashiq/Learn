<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learn</title>
<style>
.login {
	margin-top: 20%;
	width: 500px;
	height: 500px;
	font-size: 24px;
}
</style>
</head>
<body>
	<center>
		<div class="login">
			<form action="auth.do" method="POST">
				<label for="username">Username: </label>
				<input type="text" id="username" name="username"><br> <br>
				<label for="password">Password: </label>
				<input type="password" id="password" name="password"><br> <br>
				<input type="submit" value="Login">
		</div>
		</form>
	</center>
</body>
</html>