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
<script type="text/javascript">
	var selectedCourseId = 0;
	var selectedClassId = 0;
	var selectedStudentId = 0;

	function courseSelected() {

		var temp = document.getElementById("courseSelect").value.split(",");
		selectedCourseId = temp[0];
		selectedClassId = temp[1];


		document.getElementById("courseId").value = selectedCourseId;
		
		document.getElementById("studentSelect").removeAttribute('disabled');

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var students = JSON.parse(this.responseText);

				var studentSelect = document.getElementById("studentSelect");
				for ( var key in students) {

					var opt = document.createElement('option');
					opt.value = key;
					opt.innerHTML = students[key];
					studentSelect.appendChild(opt);
				}

			}
		};
		xhttp.open("GET", "getStudentsByClassId.do?classId="+selectedClassId, true);
		xhttp.send();

	}

	function studentSelected() {

		selectedStudentId = document.getElementById("studentSelect").value;
		document.getElementById("studentId").value = selectedStudentId;
		
		document.getElementById("scoreInput").removeAttribute('disabled');
		
		

	}
	function scoreEntered() {

		document.getElementById("submitButton").removeAttribute('disabled');

	}
</script>
</head>
<body>

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
				<li class="nav-item"><a class="nav-link" href="teacher.do">Home</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="">Marks</a>
				</li>
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
		<div class="row mt-5">
			<div class="col">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<label class="input-group-text" for="courseSelect">Course</label>
					</div>
					<select class="custom-select" id="courseSelect"
						onchange="courseSelected()">
						<option selected>Choose...</option>

						<%
						List<Allocation> allocations;

						try {
							allocations = (List<Allocation>) request.getAttribute("allocations");

							for (Allocation allocation : allocations) {
						%>

						<option
							value="<%=allocation.courseId() + "," + allocation.classId()%>">
							<%=allocation.className() + " - " + allocation.subjectName()%>
						</option>

						<%
						}

						} catch (Exception e) {
						System.out.print(e.getMessage());
						}
						%>

					</select>
				</div>
			</div>
		</div>


		<div class="row mt-5">
			<div class="col">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<label class="input-group-text" for="studentSelect">Student</label>
					</div>
					<select class="custom-select" id="studentSelect"
						onchange="studentSelected()" disabled>
						<option selected>Choose...</option>


					</select>
				</div>
			</div>
		</div>
		
		
		<form action="submitMark.do" method="POST">
		
		
		<input type="text" id="studentId" name="studentId" hidden>
		
		
		<input type="text" id="courseId" name="courseId" hidden>
		
		

		<div class="row mt-5">
			<div class="col">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">Score</span>
					</div>
					<input type="number" class="form-control" placeholder="Score"
						id="scoreInput" aria-label="score" name="score"
						aria-describedby="basic-addon1" onchange="scoreEntered()" disabled>
				</div>
			</div>
		</div>
		
		
		
		<div class="row mt-5">
			<div class="col">
				<div class="input-group mb-3">
					<input type="submit" class="form-control" id="submitButton"
						aria-label="score" aria-describedby="basic-addon1" disabled>
				</div>
			</div>
		</div>
		
		</form>
		

	</div>

</body>
</html>
