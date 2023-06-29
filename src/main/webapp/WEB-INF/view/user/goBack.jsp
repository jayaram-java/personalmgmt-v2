<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing page</title>


<style>
body {
	background-color: lightgreen;
}

h1 {
	color: blue;
	text-align: center;
}

.center {
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>


</head>
<body>

	<h1>Federal Bank</h1>

	<br>

	<br>

	<br>

	<br>

	<br>

	<div class="center">
		<button type="button" onclick="goBack()">Back To Login Page</button>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			function goBack() {
				window.location.replace("home");
			}

		});
	</script>


</body>
</html>