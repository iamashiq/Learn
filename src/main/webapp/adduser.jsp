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
		<div class="form-group w-25">
			<form action="insert.do" method="POST">

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

				<hr />

				<!-- fullname input -->
				<div class="form-outline mb-6">
					<input type="text" id="fullname" name="fullname"
						class="form-control" /> <label class="form-label" for=""fullname"">Name</label>
				</div>

				<!-- departmet input -->
				<div class="form-outline mb-6">
					<select name="dep_id" class="form-control" id="dep_id">
						<option value="1">Computer Science</option>
						<option value="2">Mechanical</option>
						<option value="3">Civil</option>
					</select> <label class="form-label" for="dep_id">Department</label>
				</div>

				<!-- email input -->
				<div class="form-outline mb-6">
					<input type="email" id="email" name="email" class="form-control" />
					<label class="form-label" for="email">Email</label>
				</div>
				<!-- phone input -->
				<div class="form-outline mb-6">
					<input type="text" id="phone" name="phone" class="form-control" />
					<label class="form-label" for="phone">Phone</label>
				</div>

				<!-- dob input -->
				<div class="form-outline mb-6">
					<input type="date" id="dob" name="dob" class="form-control" /> <label
						class="form-label" for="dob">Date of Birth</label>
				</div>
				<!-- gender input -->
				<div class="form-outline mb-6">
					<select name="gender" class="form-control" id="gender">
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="other">Other</option>
					</select> <label class="form-label" for="gender">Gender</label>
				</div>


				<!-- Submit button -->
				<button type="submit" class="btn btn-primary btn-block mt-4">
					Register</button>

				<!-- Register buttons -->
				<div class="text-center">
					<p>
						Already a member? <a href="login.do">Sign in</a>
					</p>

				</div>
			</form>
		</div>
	</div>


</body>
</html>
