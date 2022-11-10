<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learn</title>
<style>
.add {
	margin-top: 10%;
	width: 500px;
	height: 500px;
	font-size: 24px;
}
</style>
</head>
<body>
	<center>
		<div class="add">
			<form action="insert.do" method="POST">
			
				<label for="username">Username: </label>
				<input type="text" id="username" name="username"><br> <br>
				
				<label for="password">Password: </label>
				<input type="password" id="password" name="password"><br> <br>
				
				<hr/>
				
				<label for="fullname">Name: </label>
				<input type="text" id="fullname" name="fullname"><br> <br>
				
				<label for="phone">Phone: </label>
				<input type="number" id="phone" name="phone"><br> <br>
				
				<label for="age">Age: </label>
				<input type="number" id="age" name="age"><br> <br>
				
				<label for="gender">Gender: </label>
				<select name="gender" id="gender">
				<option value="male">Male</option>
				<option value="female">Female</option>
				<option value="other">Other</option>
				</select><br> <br>
				
				<label for="cgpa">CGPA: </label>
				<input type="number" id="cgpa" name="cgpa"><br> <br>
				
				<input type="submit" value="Add">

		</div>
		</form>
	</center>
</body>
</html>