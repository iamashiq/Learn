<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="beans.Student"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>



<body>
<center>
	Admin
	<br />
	<br />
	<br />
	
	
	<a href="/Learn/logout.do">Logout</a>
	<br />
	<br />
	<br />
	
	
	<a href="/Learn/add.do">Register</a>
	<br />
	<br />
	<br />

	<%
	List<Student> allUsers = new ArrayList<>();

		try {
			allUsers = (List<Student>) request.getAttribute("allStudents");
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Action</th>
		</tr>

		<%
		for (Student row : allUsers) {

		    pageContext.setAttribute("row_id", row.getId());
		    pageContext.setAttribute("row_name", row.getName());

		%>
		<tr>
			<td><c:out value="${row_id}" /></td>
			<td><c:out value="${row_name}" /></td>
			<td>
				<form action="/Learn/student.do" method="POST">
					<input type="text" name="id" value="<%=row.getId()%>" hidden />
					<input type="submit" value="View" />
				</form>
			</td>
		</tr>
		<%}%>

	</table>
</center>
</body>
</html>