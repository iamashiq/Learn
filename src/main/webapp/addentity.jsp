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
	function validate() {
		var name = document.getElementById("name").value;

		if (!/^[A-Za-z0-9\s]+$/.test(name)) {
			alert("Name should contain only characters or digits");
			return false;
		} 

		return true;

	}

	function entityChanged() {

		var entity = document.getElementById("entity").value;

		var totalDiv = document.getElementById("totalDiv");
		var departmentDiv = document.getElementById("departmentDiv");
		var submitButton = document.getElementById("submitButton");

		var departmentsTable = document.getElementById("departmentsTable");
		var classesTable = document.getElementById("classesTable");
		var subjectsTable = document.getElementById("subjectsTable");

		if (entity == "Department") {
			submitButton.innerHTML = "Add Department";
			departmentDiv.setAttribute("hidden", "");
			totalDiv.setAttribute("hidden", "");

			departmentsTable.removeAttribute("hidden");
			classesTable.setAttribute("hidden", "");
			subjectsTable.setAttribute("hidden", "");

		} else if (entity == "Class") {

			submitButton.innerHTML = "Add Class";
			departmentDiv.removeAttribute("hidden");

			totalDiv.setAttribute("hidden", "");

			departmentsTable.setAttribute("hidden", "");
			classesTable.removeAttribute("hidden");
			subjectsTable.setAttribute("hidden", "");

		} else if (entity == "Subject") {
			submitButton.innerHTML = "Add Subject";

			totalDiv.removeAttribute("hidden");
			departmentDiv.setAttribute("hidden", "");

			departmentsTable.setAttribute("hidden", "");
			classesTable.setAttribute("hidden", "");
			subjectsTable.removeAttribute("hidden");
		}

	}

	window.onload = function() {

		entityChanged();
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
				<li class="nav-item"><a class="nav-link" href="adduser.do">New
						User</a></li>
				<li class="nav-item"><a class="nav-link" href="allocate.do">New
						Allocation</a></li>
				<li class="nav-item"><a class="nav-link" href="addcourse.do">New
						Course</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="addentity.do">New Entity</a></li>
			</ul>
			<ul class="navbar-nav mr-auto">
			</ul>



			<form class="form-inline my-2 my-lg-0" action="logout.do"
				method="POST">

				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
			</form>
		</div>
	</nav>

	<div class="container">
		<div id="departmentsTable">

			<div class="row mt-5">
				<div class="col">

					<h3>
						<span class="badge badge-secondary">Existing Departments</span>
					</h3>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
							</tr>
						</thead>
						<tbody>


							<%
							try {
								Map<Integer, String> departments;
								departments = (Map<Integer, String>) request.getAttribute("departments");

								if (departments.size() > 0) {

									for (Map.Entry<Integer, String> dep : departments.entrySet()) {

								pageContext.setAttribute("dep_id", dep.getKey());
								pageContext.setAttribute("dep_name", dep.getValue());
							%>

							<tr>
								<td><c:out value="${dep_id}" /></td>
								<td><c:out value="${dep_name}" /></td>
							<tr />

							<%
							}

							}
							} catch (Exception e) {
							System.out.print(e.getMessage());
							}
							%>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div id="classesTable">

			<div class="row mt-5">
				<div class="col">

					<h3>
						<span class="badge badge-secondary">Existing Classes</span>
					</h3>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
								<th scope="col">Department</th>
							</tr>
						</thead>
						<tbody>


							<%
							try {
								Map<Integer, String> classes;
								classes = (Map<Integer, String>) request.getAttribute("classes");

								if (classes.size() > 0) {

								for (Map.Entry<Integer, String> cls : classes.entrySet()) {
									
								String[] temp = cls.getValue().split(",");

								pageContext.setAttribute("cls_id", cls.getKey());
								pageContext.setAttribute("cls_name", temp[0]);
								pageContext.setAttribute("cls_dname", temp[1]);
							%>

							<tr>
								<td><c:out value="${cls_id}" /></td>
								<td><c:out value="${cls_name}" /></td>
								<td><c:out value="${cls_dname}" /></td>
							<tr />

							<%
							}

							}
							} catch (Exception e) {
							System.out.print(e.getMessage());
							}
							%>

						</tbody>
					</table>
				</div>
			</div>
		</div>


		<div id="subjectsTable">

			<div class="row mt-5">
				<div class="col">

					<h3>
						<span class="badge badge-secondary">Existing Subjects</span>
					</h3>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
							</tr>
						</thead>
						<tbody>


							<%
							try {
								Map<Integer, String> subjects;
								subjects = (Map<Integer, String>) request.getAttribute("subjects");

								if (subjects.size() > 0) {

									for (Map.Entry<Integer, String> sub : subjects.entrySet()) {

								pageContext.setAttribute("sub_id", sub.getKey());
								pageContext.setAttribute("sub_name", sub.getValue());
							%>

							<tr>
								<td><c:out value="${sub_id}" /></td>
								<td><c:out value="${sub_name}" /></td>
							<tr />

							<%
							}

							}
							} catch (Exception e) {
							System.out.print(e.getMessage());
							}
							%>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<hr />

		<div class="h-100 row align-items-center justify-content-center">
			<div class="form-group w-25">
				<form action="submitentity.do" method="POST"
					onsubmit="return validate()">

					<!-- Role input -->
					<div class="form-outline mb-6">
						<select id="entity" name="entity" onchange="entityChanged()"
							class="form-control">
							<option value="Department">Department</option>
							<option value="Class">Class</option>
							<option value="Subject">Subject</option>
						</select> <label class="form-label" for="entity">Entity Type</label>
					</div>

					<hr />

					<!-- fullname input -->
					<div class="form-outline mb-6" id="fullnameDiv">
						<input type="text" id="name" name="name" class="form-control" />
						<label class="form-label" for="name">Name</label>
					</div>


					<!-- departmet input -->
					<div class="form-outline mb-6" id="departmentDiv">
						<select name="departmentId" class="form-control" id="departmentId">

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

						</select> <label class="form-label" for="departmentId">Department</label>
					</div>

					<!-- phone input -->
					<div class="form-outline mb-6" id="totalDiv">
						<input type="number" id="total" name="total" class="form-control" />
						<label class="form-label" for="total">Total</label>
					</div>



					<!-- Submit button -->
					<button type="submit" id="submitButton"
						class="btn btn-secondary btn-block mt-4">Add User</button>
				</form>
			</div>
		</div>

	</div>


</body>
</html>
