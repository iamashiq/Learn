<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Learn - Teacher</title>
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

		Teacher teacher;

		try {
			teacher = (Teacher) request.getAttribute("teacher");

			pageContext.setAttribute("teacher_id", teacher.teacherId());
			pageContext.setAttribute("teacher_departmentId", teacher.department());
			pageContext.setAttribute("teacher_name", teacher.name());
			pageContext.setAttribute("teacher_email", teacher.email());
			pageContext.setAttribute("teacher_phone", teacher.phone());
			pageContext.setAttribute("teacher_joinDate", teacher.joinDate());
			pageContext.setAttribute("teacher_gender", teacher.gender());
			pageContext.setAttribute("teacher_qualification", teacher.qualification());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Learn Teacher Portal</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>



		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="addmark.do">Marks<span
						class="sr-only">(current)</span></a></li>
			</ul>
			<ul class="navbar-nav mr-auto">
			</ul>



			<form class="form-inline my-2 my-lg-0" action="logout.do"
				method="POST">

				<div class="col">
					<h4>
						<c:out value="${teacher_name}" />
						<span class="badge badge-secondary">Teacher</span>
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

		<div class="row mt-5">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">Department</th>
							<th scope="col">Email</th>
							<th scope="col">Phone</th>
							<th scope="col">Join Date</th>
							<th scope="col">Gender</th>
							<th scope="col">Qualification</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row"><c:out value="${teacher_id}" /></th>
							<td><c:out value="${teacher_name}" /></td>
							<td><c:out value="${teacher_departmentId}" /></td>
							<td><c:out value="${teacher_email}" /></td>
							<td><c:out value="${teacher_phone}" /></td>
							<td><c:out value="${teacher_joinDate}" /></td>
							<td><c:out value="${teacher_gender}" /></td>
							<td><c:out value="${teacher_qualification}" /></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
