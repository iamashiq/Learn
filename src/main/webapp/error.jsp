<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learn</title>

<script type="text/javascript">

function redirect()
{
	window.setTimeout(function(){
		history.go(-1);

    }, 5000);
}

window.onload = function() {

	redirect();
}

</script>
</head>
<body>
<center style="margin-top:200px">
<h1>Error</h1>
<p><%= request.getAttribute("error_message") %></p4>
</center>
</body>
</html>