<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@page import="beans.*"%>
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

<script>
	function roleChanged() {

		var role = document.getElementById("role").value;

		var submitButton = document.getElementById("submitButton");
		var genderDiv = document.getElementById("genderDiv");
		var dobDiv = document.getElementById("dobDiv");
		var classDiv = document.getElementById("classDiv");
		var departmentDiv = document.getElementById("departmentDiv");
		var qualificationDiv = document.getElementById("qualificationDiv");
		var submitButton = document.getElementById("submitButton");

		if (role != "3") {
			genderDiv.removeAttribute("hidden");
			dobDiv.removeAttribute("hidden");
			classDiv.removeAttribute("hidden");
			departmentDiv.removeAttribute("hidden");
			qualificationDiv.removeAttribute("hidden");
		}
		if (role == "2") {
			submitButton.innerHTML = "Add Teacher";
			document.getElementById("submitButton")
			departmentDiv.removeAttribute("hidden");
			joindateDiv.removeAttribute("hidden");
			qualificationDiv.removeAttribute("hidden");

			dobDiv.setAttribute("hidden", "");
			classDiv.setAttribute("hidden", "");
		} else if (role == "1") {

			submitButton.innerHTML = "Add Student";
			dobDiv.removeAttribute("hidden");
			classDiv.removeAttribute("hidden");

			departmentDiv.setAttribute("hidden", "");
			joindateDiv.setAttribute("hidden", "");
			qualificationDiv.setAttribute("hidden", "");

		} else if (role == "3") {
			submitButton.innerHTML = "Add Admin";
			genderDiv.setAttribute("hidden", "");
			dobDiv.setAttribute("hidden", "");
			classDiv.setAttribute("hidden", "");
			departmentDiv.setAttribute("hidden", "");
			joindateDiv.setAttribute("hidden", "");
			qualificationDiv.setAttribute("hidden", "");
		}

	}

	window.onload = function() {

		roleChanged();
	}
</script>

<body>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Learn Admin Portal</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>



		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="admin.do">Home</a></li>
				<li class="nav-item active"><a class="nav-link" href="adduser.do">New User</a></li>
				<li class="nav-item"><a class="nav-link" href="allocate.do">New Allocation</a></li>
				<li class="nav-item"><a class="nav-link" href="addcourse.do">New Course</a></li>
				<li class="nav-item"><a class="nav-link" href="addentity.do">New Entity</a></li>
			</ul>
			<ul class="navbar-nav mr-auto">
			</ul>

			<form class="form-inline my-2 my-lg-0" action="logout.do"
				method="POST">

				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
			</form>
		</div>
	</nav>




	<div class="h-100 d-flex align-items-center justify-content-center">
		<div class="form-group w-25">
			<form action="submituser.do" method="POST">

				<!-- username input -->

				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text" id="username">Username</span>
					</div>
					<input type="text" class="form-control" id="username"
						aria-label="username" name="username" aria-describedby="username">
				</div>

				<!-- Password input -->
				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text" id="password">Password</span>
					</div>
					<input type="password" class="form-control" id="password"
						aria-label="password" name="password" aria-describedby="password">
				</div>

				<!-- Role input -->
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<label class="input-group-text" for="role">Role</label>
					</div>
					<select class="custom-select" id="role" name="role"
						onchange="roleChanged()">
						<option value="1">Student</option>
						<option value="2">Teacher</option>
						<option value="3">Admin</option>
					</select>
				</div>

				<hr />


				<!-- fullname input -->
				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text">Name</span>
					</div>
					<input type="text" class="form-control" id="fullname"
						aria-label="fullname" name="fullname" aria-describedby="fullname">
				</div>

				<!-- Class input -->
				<div class="input-group mb-4" id="classDiv">
					<div class="input-group-prepend">
						<label class="input-group-text" for="classId">Class</label>
					</div>
					<select class="custom-select" id="classId" name="classId">

						<%
						try {
							Map<Integer, String> classes = (Map<Integer, String>) request.getAttribute("classes");

							if (classes.size() > 0) {

								for (Map.Entry<Integer, String> d : classes.entrySet()) {
							pageContext.setAttribute("row_cid", d.getKey());
							pageContext.setAttribute("row_cname", d.getValue());
						%>

						<option value="<c:out value="${row_cid}" />"><c:out
								value="${row_cname}" />
						</option>


						<%
						}

						}
						} catch (Exception e) {
						System.out.print(e.getMessage());
						}
						%>

					</select>
				</div>


				<!-- departmet input -->
				<div class="input-group mb-4" id="departmentDiv">
					<div class="input-group-prepend">
						<label class="input-group-text" for="departmentId">Department</label>
					</div>
					<select class="custom-select" id="departmentId" name="departmentId">

						<%
						try {
							Map<Integer, String> departments = (Map<Integer, String>) request.getAttribute("departments");

							if (departments.size() > 0) {

								for (Map.Entry<Integer, String> d : departments.entrySet()) {
							pageContext.setAttribute("row_did", d.getKey());
							pageContext.setAttribute("row_dname", d.getValue());
							System.out.println(d.getKey() + " X");
						%>

						<option value="<c:out value="${row_did}" />"><c:out
								value="${row_dname}" />
						</option>


						<%
						}

						}
						} catch (Exception e) {
						System.out.print(e.getMessage());
						}
						%>


					</select>
				</div>



				<!-- email input -->
				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text">Email</span>
					</div>
					<input type="mail" class="form-control" id="email"
						aria-label="email" name="email" aria-describedby="email">
				</div>
				
				
				<!-- phone input -->
				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text">Phone</span>
					</div>
					<input type="mail" class="form-control" id="phone"
						aria-label="phone" name="phone" aria-describedby="phone">
				</div>

				<!-- dob input -->
				<div class="input-group mb-4" id="dobDiv">
					<div class="input-group-prepend">
						<span class="input-group-text">Date of Birth</span>
					</div>
					<input type="date" class="form-control" id="dob"
						aria-label="dob" name="dob" aria-describedby="dob">
				</div>

				<!-- joindate input -->
				<div class="input-group mb-4" id="joindateDiv">
					<div class="input-group-prepend">
						<span class="input-group-text">Join Date</span>
					</div>
					<input type="date" class="form-control" id="joindate"
						aria-label="joindate" name="joindate" aria-describedby="joindate">
				</div>

				<!-- qualification input -->
				<div class="input-group mb-4" id="qualificationDiv">
					<div class="input-group-prepend">
						<span class="input-group-text">Qualification</span>
					</div>
					<input type="text" class="form-control" id="qualification"
						aria-label="qualification" name="qualification" aria-describedby="qualification">
				</div>

				<!-- gender input -->
				<div class="input-group mb-4" id="genderDiv">
					<div class="input-group-prepend">
						<label class="input-group-text" for="gender">Gender</label>
					</div>
					<select class="custom-select" id="gender" name="gender">
						<option value="MALE">Male</option>
						<option value="FEMALE">Female</option>
						<option value="OTHER">Other</option>
					</select>
				</div>

				<!-- Submit button -->
				<button type="submit" id="submitButton"
					class="btn btn-info btn-block mt-4">Add User</button>
			</form>
		</div>
	</div>


</body>
</html>
