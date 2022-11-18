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
				<li class="nav-item"><a class="nav-link" href="adduser.do">Add
						user</a></li>
				<li class="nav-item"><a class="nav-link" href="allocate.do">Allocate
						course</a></li>
			</ul>
			<ul class="navbar-nav mr-auto">
			</ul>



			<form class="form-inline my-2 my-lg-0" action="logout.do"
				method="POST">

				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
			</form>
		</div>
	</nav>
	
	<form action="submitallocation.do" method="POST">
	
	<div class="container">
	
			<div class="row mt-5">
			<div class="col">

				<h3>
					<span class="badge badge-secondary">Existing Allocations</span>
				</h3>
			</div>
		</div>



		<div class="row mt-5">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Class</th>
							<th scope="col">Subject</th>
						</tr>
					</thead>
					<tbody>
					
					
						<%
						try {
							List<Allocation> allocations;
							allocations = (List<Allocation>) request.getAttribute("allocations");

							if(allocations.size()>0)
							{
								
							for (Allocation alloc : allocations) {

								pageContext.setAttribute("alloc_id", alloc.allocId());
								pageContext.setAttribute("alloc_class", alloc.className());
								pageContext.setAttribute("alloc_subject", alloc.subjectName());
						%>
						<tr>
							<td><c:out value="${alloc_id}" /></td>
							<td><c:out value="${alloc_class}" /></td>
							<td><c:out value="${alloc_subject}" /></td>
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
		
	
	
	
		<div class="row mt-5">
			<div class="col">
				<h3>
					<span class="badge badge-secondary">Allocate course</span>
				</h3>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
					<label class="input-group-text" for="teacherSelect">Teacher</label>
					</div>
					<select class="custom-select" id="teacherSelect" name="teacherId">
						<option selected>Choose...</option>
						
						<%
						try
						{
						Map<Integer, String> teachers = (Map<Integer, String>) request.getAttribute("teachers");

						if (teachers.size() > 0)
						{

							for (Map.Entry<Integer, String> t : teachers.entrySet()) 
							{
							pageContext.setAttribute("row_tid", t.getKey());
							pageContext.setAttribute("row_tname", t.getValue());
						%>

						<option value="<c:out value="${row_tid}" />"><c:out value="${row_tname}" />
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
					<label class="input-group-text" for="courseSelect">Course</label>
					</div>
					<select class="custom-select" id="courseSelect" name="courseId">
						<option selected>Choose...</option>
						
					<%
						try
						{
						Map<Integer, String> courses = (Map<Integer, String>) request.getAttribute("courses");

						if (courses.size() > 0)
						{

							for (Map.Entry<Integer, String> t : courses.entrySet()) 
							{
							pageContext.setAttribute("row_crid", t.getKey());
							pageContext.setAttribute("row_crname", t.getValue());
						%>

						<option value="<c:out value="${row_crid}" />"><c:out value="${row_crname}" />
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
