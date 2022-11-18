<%@page import="beans.Student"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>



<body>

	Student
	<br />
	<br />

	<%
	Student user;

		user = (Student) request.getAttribute("user");
	    pageContext.setAttribute("row_id", user.getId());
	    pageContext.setAttribute("row_name", user.getName());
	    pageContext.setAttribute("row_email", user.getEmail());
	    pageContext.setAttribute("row_phone", user.getPhone());
	    pageContext.setAttribute("row_dob", user.getDob());
	    pageContext.setAttribute("row_gender", user.getGender());
	    
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>DOB</th>
			<th>Gender</th>
		</tr>
		<tr>
			<td><c:out value="${row_id}" /></td>
			<td><c:out value="${row_name}" /></td>
			<td><c:out value="${row_email}" /></td>
			<td><c:out value="${row_phone}" /></td>
			<td><c:out value="${row_dob}" /></td>
			<td><c:out value="${row_gender}" /></td>
		</tr>


	</table>

</body>
</html>