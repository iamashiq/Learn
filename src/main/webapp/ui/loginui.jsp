<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Learn - Login</title>
<meta charset="UTF-8">
</head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<style>
.center {
	padding: 10px;
	background-color: #c0c0c0;
}

html, body {
	height: 100%
}
</style>
<body>
	<div class="h-100 d-flex align-items-center justify-content-center">
		<div class="center">
			<form action="auth.do" method="POST">

				<div class="p-2 text-center bg-dark mb-4 text-white">
					<h1 class="mb-3">Learn</h1>
				</div>
				<!-- username input -->
				<div class="form-outline mb-6">
					<input type="text" id="username" name="username"
						class="form-control" /> <label class="form-label" for="username">Username</label>
				</div>

				<!-- Password input -->
				<div class="form-outline mb-6">
					<input type="password" id="password" name="password"
						class="form-control" /> <label class="form-label" for="password">Password</label>
				</div>



				<!-- Submit button -->
				<button type="submit" class="btn btn-primary btn-block mb-4">Sign
					in</button>

				<!-- Register buttons -->
				<div class="text-center">
					<p>
						Not a member? <a href="#!">Register</a>
					</p>

				</div>
			</form>
		</div>
	</div>


</body>
</html>
