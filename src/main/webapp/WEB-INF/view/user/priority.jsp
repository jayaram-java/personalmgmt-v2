<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Priority</title>

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
	height: 1500px; /* Used in this example to enable scrolling */
}
</style>

<script type="text/javascript" src="resources/js/priority.js"></script>

<body>

	<section id=header> <%@ include file="header.jsp"%>

	<br>

	</section>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li>Priority</li>
		</ul>

	</div>



	<div align="right">


		<input type="button" class="button" value="New Priority" id="priorityb">&nbsp
		&nbsp &nbsp &nbsp



	</div>

	<br>

	<br>

	<div>
		<table id="prioritytable">
			<thead>
				<tr>
					<th>Priority-id</th>
					<th>Name</th>
					<th>Priority level</th>
					<th>Date</th>
					 <th>Edit</th>
				</tr>
			</thead>
       
		</table>

	</div>
	
	
	<br>
	<br>
	<br>
	
	<div class="form-popup" id="myForm" align="center">
		<form>
			<div class="container">
				<h1 align="left">Priority</h1>

				<hr>

				<div class="row">

					<div class="column">
						<label for="startdate"><b> Name *</b></label> <input
							type="text" placeholder="Enter Priority Name" name="priorityname"
							id="priorityname" required>
					</div>

					<div class="column">
						<label for="level"><b> Level *</b></label> <input
							type="text" placeholder="Enter  level"
							name="level" id="level" required>
					</div>
				</div>

				<div class="row">
					<div class="column">
						<label for="date"><b> Date *</b></label><br> <input
							type="date" id="date" name="date">
					</div>
				


				</div>

				

				<div align="left">

					<label for="phone"><b>* Indicates mandatory fields</b></label>

				</div>
				<hr>



				<div class="row">

					<div class="column">

						<input type="button" class="registerbtn" value="Save"
							id="savePriority" />


					</div>

					<div class="column">
						<button type="button" class="registerbtn" onClick="refreshPage()">Close</button>

					</div>

				</div>
			</div>


		</form>


	</div>

	<input type="hidden" id="priorityid"> </section>
	
	

</body>
</html>