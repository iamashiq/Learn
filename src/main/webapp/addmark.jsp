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
	function validate() {
		var score = document.getElementById("score").value;

		if (Number(score) > 100 || Number(score) < 0) {
			alert("Score should be between than 0 and 100");
			return false;
		}

		return true;

	}

	var selectedCourseId = 0;
	var selectedClassId = 0;
	var selectedStudentId = 0;

	function courseSelected() {


		document.getElementById("studentId").value = "";

		var temp = document.getElementById("courseSelect").value.split(",");
		selectedCourseId = temp[0];
		selectedClassId = temp[1];
		
		var studentSelect =  document.getElementById("studentSelect");
		studentSelect.innerHTML = "";	
		var opt = document.createElement('option');
		opt.disabled = true;
		opt.selected = true;
		opt.innerHTML = "Choose..";
		studentSelect.appendChild(opt);

		document.getElementById("courseId").value = selectedCourseId;

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
		xhttp.open("GET", "getFilteredStudents.do?classId=" + selectedClassId+"&courseId="+ selectedCourseId,
				true);
		xhttp.send();

	}

	function studentSelected() {

		selectedStudentId = document.getElementById("studentSelect").value;
		document.getElementById("studentId").value = selectedStudentId;

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
			<h3>
				<span class="badge badge-secondary">Enter Marks</span>
			</h3>
		</div>

		<div class="row mt-3">
			<div class="col">
				<div class="input-group mb-5">
					<div class="input-group-prepend">
						<label class="input-group-text" for="courseSelect">Course</label>
					</div>
					<select class="custom-select" id="courseSelect"
						onchange="courseSelected()">
						<option selected>Choose...</option>

						<%
						List<TeacherAllocation> allocations;

						try {
							allocations = (List<TeacherAllocation>) request.getAttribute("allocations");

							for (TeacherAllocation allocation : allocations) {
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


		<div class="row">
			<div class="col">
				<div class="input-group mb-5">
					<div class="input-group-prepend">
						<label class="input-group-text" for="studentSelect">Student</label>
					</div>
					<select class="custom-select" id="studentSelect"
						onchange="studentSelected()" required>
						
						<option disabled selected>Choose..</option>


					</select>
				</div>
			</div>
		</div>


		<form action="submitmark.do" method="POST"
			onsubmit="return validate()">


			<input type="text" id="studentId" name="studentId" required hidden>
			 <input	type="text" id="courseId" name="courseId" required hidden>



			<div class="row">
				<div class="col">
					<div class="input-group mb-5">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">Score</span>
						</div>
						<input type="number" class="form-control" placeholder="Score"
							id="score" aria-label="score" name="score"
							aria-describedby="basic-addon1" required>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="col">
					<div class="input-group mb-5">
						<input type="submit" class="form-control" id="submitButton"
							aria-label="score" aria-describedby="basic-addon1">
					</div>
				</div>
			</div>

		</form>


	</div>

</body>
</html>
