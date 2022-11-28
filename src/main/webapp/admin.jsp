<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Learn - Admin</title>
<meta charset="UTF-8">

<%@ page import="java.util.*"%>
<%@page import="beans.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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

</head>
<body>

	<%
	Admin admin;

	try {
		admin = (Admin) request.getAttribute("admin");

		pageContext.setAttribute("admin_id", admin.adminId());
		pageContext.setAttribute("admin_name", admin.name());
		pageContext.setAttribute("admin_email", admin.email());
		pageContext.setAttribute("admin_phone", admin.phone());
	} catch (Exception e) {
		System.out.print(e.getMessage());
	}
	%>

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
				<li class="nav-item active"><a class="nav-link" href="admin.do">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="adduser.do">New
						User</a></li>
				<li class="nav-item"><a class="nav-link" href="allocate.do">New
						Allocation</a></li>
				<li class="nav-item"><a class="nav-link" href="addcourse.do">New
						Course</a></li>
				<li class="nav-item"><a class="nav-link" href="addentity.do">New
						Entity</a></li>
			</ul>
			<ul class="navbar-nav mr-auto">
			</ul>



			<form class="form-inline my-2 my-lg-0" action="logout.do"
				method="POST">

				<div class="col">
					<h4>
						<c:out value="${admin_name}" />
						<span class="badge badge-secondary">Admin</span>
					</h4>
				</div>

				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
			</form>
		</div>
	</nav>



	<div class="container">

		<div class="row mt-5">
			<div class="col">
				<h3>
					<span class="badge badge-secondary">Details</span>
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
							<th scope="col">Email</th>
							<th scope="col">Phone</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row"><c:out value="${admin_id}" /></th>
							<td><c:out value="${admin_name}" /></td>
							<td><c:out value="${admin_email}" /></td>
							<td><c:out value="${admin_phone}" /></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
		
		<hr/>

		<div class="row mt-5">
			<div class="col">
				<h3>
					<span class="badge badge-secondary">Students</span>
				</h3>
			</div>
		</div>


		<div class="row">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">Class</th>
							<th scope="col">Department</th>
						</tr>
					</thead>
					<tbody>

						<%
						try {
							Map<Integer, String> students;
							students = (Map<Integer, String>) request.getAttribute("students");

							if (students.size() > 0) {

							int index = 1;
							for (Map.Entry<Integer, String> stud : students.entrySet()) {
								
							String[] temp = stud.getValue().split(",");

							pageContext.setAttribute("stud_index", index);
							pageContext.setAttribute("stud_id", stud.getKey());
							pageContext.setAttribute("stud_name", temp[0]);
							pageContext.setAttribute("stud_class", temp[1]);
							pageContext.setAttribute("stud_dep", temp[2]);
						%>

						<tr>
							<td><c:out value="${stud_index}" /></td>
							<td><c:out value="${stud_id}" /></td>
							<td><c:out value="${stud_name}" /></td>
							<td><c:out value="${stud_class}" /></td>
							<td><c:out value="${stud_dep}" /></td>
						<tr />

						<%
						index++;
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


		<div class="row mt-5">
			<div class="col">
				<h3>
					<span class="badge badge-secondary">Teachers</span>
				</h3>
			</div>
		</div>


		<div class="row">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">Department</th>
						</tr>
					</thead>
					<tbody>
						<%
						try {
							Map<Integer, String> teachers;
							teachers = (Map<Integer, String>) request.getAttribute("teachers");

							if (teachers.size() > 0) {
							
							int index = 1;
							for (Map.Entry<Integer, String> tcr : teachers.entrySet()) {
								
							String[] temp = tcr.getValue().split(" - ");

							pageContext.setAttribute("tcr_index", index);
							pageContext.setAttribute("tcr_id", tcr.getKey());
							pageContext.setAttribute("tcr_name", temp[0]);
							pageContext.setAttribute("tcr_dep", temp[1]);
						%>

						<tr>
							<td><c:out value="${tcr_index}" /></td>
							<td><c:out value="${tcr_id}" /></td>
							<td><c:out value="${tcr_name}" /></td>
							<td><c:out value="${tcr_dep}" /></td>
						<tr />

						<%
						index++;
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



		<div class="row mt-5">
			<div class="col">
				<h3>
					<span class="badge badge-secondary">Admins</span>
				</h3>
			</div>
		</div>


		<div class="row">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
						</tr>
					</thead>
					<tbody>

						<%
						try {
							Map<Integer, String> admins;
							admins = (Map<Integer, String>) request.getAttribute("admins");

							if (admins.size() > 0) {
								int index = 1;

								for (Map.Entry<Integer, String> adm : admins.entrySet()) {

							pageContext.setAttribute("adm_index", index);
							pageContext.setAttribute("adm_id", adm.getKey());
							pageContext.setAttribute("adm_name", adm.getValue());
						%>

						<tr>
							<td><c:out value="${adm_index}" /></td>
							<td><c:out value="${adm_id}" /></td>
							<td><c:out value="${adm_name}" /></td>
						<tr />

						<%
						index++;
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

</body>
</html>
