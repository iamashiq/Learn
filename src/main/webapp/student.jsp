<%@page import="beans.Student"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
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
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Phone</th>
			<th>Age</th>
			<th>Gender</th>
			<th>CGPA</th>
		</tr>
		<tr>
			<td><%=user.getId()%></td>
			<td><%=user.getName()%></td>
			<td><%=user.getPhone()%></td>
			<td><%=user.getAge()+""%></td>
			<td><%=user.getGender()+""%></td>
			<td><%=user.getCgpa()+""%></td>
		</tr>


	</table>

</body>
</html>