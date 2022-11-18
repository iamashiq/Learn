<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Learn - Student</title>
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
	

		Student student;
		List<StudentMark> marks;

		try {
			student = (Student) request.getAttribute("student");

			pageContext.setAttribute("student_id", student.studentId());
			pageContext.setAttribute("student_class", student.classname());
			pageContext.setAttribute("student_dept", student.deptname());
			pageContext.setAttribute("student_name", student.name());
			pageContext.setAttribute("student_email", student.email());
			pageContext.setAttribute("student_phone", student.phone());
			pageContext.setAttribute("student_dob", student.dob());
			pageContext.setAttribute("student_gender", student.gender());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	%>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Learn Student Portal</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			</ul>
			<form class="form-inline my-2 my-lg-0" action="logout.do"
				method="POST">
				
				<div class="col">

					<h4>
					<c:out value="${student_name}" />
					<span class="badge badge-secondary">Student</span>
				</h4>
				</div>
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log
					out</button>
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
							<th scope="col">Class</th>
							<th scope="col">Department</th>
							<th scope="col">Email</th>
							<th scope="col">Phone</th>
							<th scope="col">DOB</th>
							<th scope="col">Gender</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row"><c:out value="${student_id}" /></th>
							<td><c:out value="${student_name}" /></td>
							<td><c:out value="${student_class}" /></td>
							<td><c:out value="${student_dept}" /></td>
							<td><c:out value="${student_email}" /></td>
							<td><c:out value="${student_phone}" /></td>
							<td><c:out value="${student_dob}" /></td>
							<td><c:out value="${student_gender}" /></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>

		<div class="row mt-5">
			<div class="col">

				<h3>
					<span class="badge badge-secondary">Marks</span>
				</h3>
			</div>
		</div>

		<div class="row mt-5">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Subject</th>
							<th scope="col">Score</th>
							<th scope="col">Total</th>
						</tr>
					</thead>

					<tbody>

						<%
						try {
							marks = (List<StudentMark>) request.getAttribute("marks");

							if(marks.size()>0)
							{
								
							for (StudentMark mark : marks) {

								pageContext.setAttribute("mark_id", mark.subjectId());
								pageContext.setAttribute("mark_subject", mark.subjectName());
								pageContext.setAttribute("mark_score", mark.subjectScore());
								pageContext.setAttribute("mark_total", mark.subjectTotal());
						%>
						<tr>
							<td><c:out value="${mark_id}" /></td>
							<td><c:out value="${mark_subject}" /></td>
							<td><c:out value="${mark_score}" /></td>
							<td><c:out value="${mark_total}" /></td>
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

</body>
</html>
