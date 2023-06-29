<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Developing</title>

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


<script type="text/javascript" src="resources/js/developing.js"></script>
<body>

	<%-- <section id=header> <%@ include file="header.jsp"%>

	<br>

	</section> --%>

	<section id="main-menu"> <%@ include file="navigate.jsp"%>

	</section>






	<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li>Developing</li>
		</ul>

	</div>


	<div align="right">
		<input type="button" class="button" value="jira request"
			id="propertyb">&nbsp &nbsp &nbsp &nbsp

	</div>
	<br>

	<br>

	<div class="container" style="width: 800px; margin-left: -15px">

		<div class="panel panel-primary" id="task-panel">

			<strong>Leaders details</strong>

		</div>

		<table id="leadersTable">

			<tfoot>

				<tr>

					<td></td>

				</tr>

			</tfoot>
			<tr>

				<th>Name</th>
				<th>age</th>
				<th>Experience</th>
				<th>Delete</th>

			</tr>

			<tbody id="service1">

				<tr class="tableRow">

					<td class="nameId"><input type="text"
						class="form-control text-center name" placeholder="Enter name"
						name="name" id="name"></td>

					<td class="ageId"><input type="text"
						class="form-control text-center age" placeholder="Enter age"
						name="age" id="age"></td>

					<td class="exId"><input type="text"
						class="form-control text-center experience"
						placeholder="Enter experience" name="experience" id="experience"></td>

					<td class="removeId">

						<button class="btn btn-danger remove" type="button">-</button>

					</td>
				</tr>
				
					<tr class="tableRow">

					<td class="nameId"><input type="text"
						class="form-control text-center name" placeholder="Enter name"
						name="name" id="name"></td>

					<td class="ageId"><input type="text"
						class="form-control text-center age" placeholder="Enter age"
						name="age" id="age"></td>

					<td class="exId"><input type="text"
						class="form-control text-center experience"
						placeholder="Enter experience" name="experience" id="experience"></td>

					<td class="removeId">

						<button class="btn btn-danger remove" type="button">-</button>

					</td>
				</tr>
				
			</tbody>


		</table>


	</div>













	<input type="hidden" id="propertyid"> </section>
</body>
</html>


