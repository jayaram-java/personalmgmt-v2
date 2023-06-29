<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">



<script src="resources/jquery/jquery.min.js"></script>

<!-- <script type="text/javascript"  src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script> -->

<!-- <link href="//cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"> -->

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">

<!-- 	<script type="text/javascript"  src="//cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script> -->

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

<script type="text/javascript" src="resources/js/employee.js"></script>

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
			<li>Task</li>
		</ul>

	</div>

	<%

 if((String)session.getAttribute("active") == "employee"){
	 
	 %>

	<div align="right">


		<input type="button" class="button" value="New Task" id="employeeb">&nbsp
		&nbsp &nbsp &nbsp



	</div>

	<br>

	<br>

	<div>
		<table id="employeetasktable">
			<thead>
				<tr>
					<th>Task-id</th>
					<th>Task Name</th>
					<th>Task description</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>
			</thead>
		</table>

	</div>
	
	<%
	}


%>
	
		<%

 if((String)session.getAttribute("active") == "admin"){
	 
	 %>
	
	<div>
		<table id="employeetaskadmintable">
			<thead>
				<tr>
					<th>Task-id</th>
					<th>Task Name</th>
					<th>Task description</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Status</th>
					<th>Employee Name</th>
					
				</tr>
			</thead>
		</table>

	</div>
	
<%
	}


%>
	<br>
	<br>
	<br>
	<br>





	<div class="form-popup" id="myForm" align="center">
		<form>
			<div class="container">
				<h1 align="left">Employee Task</h1>

				<hr>

				<div class="row">

					<div class="column">
						<label for="startdate"><b>Task Name *</b></label> <input
							type="text" placeholder="Enter Task Name" name="taskname"
							id="taskname" required>
					</div>

					<div class="column">
						<label for="taskdescription"><b>Task Description *</b></label> <input
							type="text" placeholder="Enter Task Description"
							name="taskdescription" id="taskdescription" required>
					</div>
				</div>

				<div class="row">
					<div class="column">
						<label for="startdate"><b>Start Date *</b></label><br> <input
							type="date" id="startdate" name="startdate">
					</div>

					<div class="column">
						<label for="enddate"><b>End Date *</b></label><br> <input
							type="date" id="enddate" name="enddate">
					</div>

				</div>

				<div class="row">
					<div class="column">
						<label for="startdate"><b>Status *</b></label><input type="text"
							placeholder="Enter status" name="status" id="status" required>
					</div>
					<div class="column"></div>

				</div>
				
				<div align ="left">
				
				<label for="phone"><b>* Indicates mandatory fields</b></label>

				</div>
				<hr>
				
				

				<div class="row">

					<div class="column">

						<input type="button" class="registerbtn" value="Save"
							id="saveemployeetask" />

						<!-- 	<input type="text" class="registerbtn" id="saveemployeetask" value = save> -->


						<!-- <button type="button" class="registerbtn" id="saveemployeetask">Save</button> -->

					</div>

					<div class="column">
						<button type="button" class="registerbtn" onClick="refreshPage()">Close</button>

						<!-- <button type="button" class="registerbtn" onClick="refreshPage()"  id="close">Close</button> -->

					</div>

				</div>
			</div>


		</form>


	</div>

	<input type="hidden" id="taskid"> </section>
</body>
</html>