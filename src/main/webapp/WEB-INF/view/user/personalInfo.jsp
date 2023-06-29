<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Info</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<script src="resources/jquery/jquery.min.js"></script>

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript"
	src="resources/jquery/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="resources/css/sweetalert.min.css">

<script src="resources/jquery/sweetalert.min.js"></script>

</head>
<style>
body {
	margin: 0;
}

.navbar {
	overflow: hidden;
	background-color: #333;
	position: fixed;
	top: 0;
	width: 100%;
}

.navbar a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.navbar a:hover {
	background: #ddd;
	color: black;
}

.main {
	padding: 16px;
	margin-top: 30px;
	height: 1500px; 
}
</style>


<script type="text/javascript" src="resources/js/personalInfo.js"></script>
<body>

	<%-- <section id=header> <%@ include  file="header.jsp"%>

	<br>

	</section> --%>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	
	
	
	

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Personal</a></li>
			<li>Personal Info</li>
		</ul>

	</div>

	


	
	<br>

	<br>

	<div>
		<table id="personalinfodataTable">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Office Name</th>
					<th>age</th>
					<th>Designation</th>
					<th>Link</th>
				</tr>
			</thead>
		</table>
	</div>

	
	
	
	<br>
	



	<input type="hidden" id="propertyid"> </section>
</body>
</html>


