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

<script type="text/javascript">
function classSelected()
{
	document.getElementById("subjectSelect").innerHTML = "";
	selectedClassId = document.getElementById("classSelect").value;

	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			
			var subjects = JSON.parse(this.responseText);

			var subjectSelect = document.getElementById("subjectSelect");
			
			for ( var key in subjects) {

				var opt = document.createElement('option');
				opt.value = key;
				opt.innerHTML = subjects[key];
				subjectSelect.appendChild(opt);
			}

		}
	};
	xhttp.open("GET", "getFilteredSubjects.do?classId="+selectedClassId, true);
	xhttp.send();
	
}

</script>

</head>
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
				<li class="nav-item"><a class="nav-link" href="adduser.do">New User</a></li>
				<li class="nav-item"><a class="nav-link" href="allocate.do">New Allocation</a></li>
				<li class="nav-item active"><a class="nav-link" href="addcourse.do">New Course</a></li>
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
	
	<form action="submitcourse.do" method="POST">
	
	<div class="container">
	
	
		<div  id="subjectsTable">

			<div class="row mt-5">
				<div class="col">

					<h3>
						<span class="badge badge-secondary">Existing Courses</span>
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
								<th scope="col">Class - Subject</th>
							</tr>
						</thead>
						<tbody>


							<%
							try {
								Map<Integer, String> courses;
								courses = (Map<Integer, String>) request.getAttribute("courses");

								if (courses.size() > 0) {
									int index =1;

									for (Map.Entry<Integer, String> crs : courses.entrySet()) {

								pageContext.setAttribute("crs_index", index);
								pageContext.setAttribute("crs_id", crs.getKey());
								pageContext.setAttribute("crs_name", crs.getValue());
							%>

							<tr>
								<td><c:out value="${crs_index}" /></td>
								<td><c:out value="${crs_id}" /></td>
								<td><c:out value="${crs_name}" /></td>
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
	
	<hr/>
	
		<div class="row mt-5">
			<div class="col">
				<h3>
					<span class="badge badge-secondary">Create New Course</span>
				</h3>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
					<label class="input-group-text" for="classSelect">Class</label>
					</div>
					<select class="custom-select" id="classSelect" name="classId" onchange="classSelected()">
						<option selected>Choose...</option>
						
						<%
						try
						{
						Map<Integer, String> classes = (Map<Integer, String>) request.getAttribute("classes");

						if (classes.size() > 0)
						{

							for (Map.Entry<Integer, String> cl : classes.entrySet()) 
							{
								System.out.println(cl.getKey()+" "+cl.getValue());
							pageContext.setAttribute("row_clid", cl.getKey());
							pageContext.setAttribute("row_clname", cl.getValue());
						%>

						<option value="<c:out value="${row_clid}" />"><c:out value="${row_clname}" />
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
				
			</div>
			<div class="col">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
					<label class="input-group-text" for="subjectSelect">Subject</label>
					</div>
					<select class="custom-select" id="subjectSelect" name="subjectId">
						<option selected>Choose...</option>
					</select>
				</div>
			
			</div>
		</div>
		
		<div class="row">
			<div class="col">
			</div>
			<div class="col">
			
				<div class="input-group mb-3">
					<input type="submit" class="form-control btn btn-secondary" id="submitButton"
						aria-label="score" aria-describedby="basic-addon1">
				</div>
			
			</div>
			
			<div class="col">
			</div>
		</div>
	</div>
	</form>
	

</body>
</html>
