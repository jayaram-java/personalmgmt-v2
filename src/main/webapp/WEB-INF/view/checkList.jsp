<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check List</title>

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

<script type="text/javascript" src="resources/js/checkList.js"></script>
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
			<li>Check List</li>
		</ul>

	</div>

	


	<div align="right">
		<input type="button" class="button" value="New Check List" id="checklistb">&nbsp
		&nbsp &nbsp &nbsp



	</div>
	<br>

	<br>

	<div>
		<table id="checklistdataTable">
			<thead>
				<tr>
					<th>checklist-id</th>
					<th>Name</th>
					<th>Parent</th>
					<th>Created Date</th>
					<th>Edit</th>
				</tr>
			</thead>
		</table>
	</div>

	<div align="right">
	
	 <select name="email" id="email">
	   <option value="">Select the correct email id</option>
    <option value="jayaramp51096@gmail.com">jayaramp51096@gmail.com</option>
    <option value="priya31998@gmail.com">priya31998@gmail.com</option>
   
  </select>
	
	<input type="button" class="button" value="Sending Email" id="emailsent">&nbsp
		&nbsp &nbsp &nbsp


		<input type="button" class="button" value="PDF" id="pdfcreate"> &nbsp
		&nbsp &nbsp &nbsp

	</div>
	
	
	<br>
	<br>
	<br>


	<div class="form-popup" id="myForm" align="center">
		<form>
			<div class="container">
				<h1 align="left">Check List</h1>

				<hr>

				<div class="row">

						<div class="column">
						<label for="startdate"><b>Name *</b></label> <input 
							type="text" placeholder="Enter requirement" name="checklistname"
							id="checklistname" required>
					
						</div>
						
						<div class="column">
						 <label for="parent"><b>Parent *</b></label><br><br>
						 
  <select name="parent" id="parent">
    <option value="">Select</option>
     <option value="Splenta Systems">Splenta Systems</option>
  </select>
						
					
						
						</div>
					
				</div>

				
				

				<div align="left">

					<label for="phone"><b>* Indicates mandatory fields</b></label>

				</div>
				<hr>



				<div class="row">

					<div class="column">

						<input type="button" class="registerbtn" value="Save"
							id="savechecklist" />


					</div>

					<div class="column">
						<button type="button" class="registerbtn" onClick="refreshPage()">Close</button>

					</div>

				</div>
			</div>


		</form>


	</div>

	<input type="hidden" id="checklistid"> </section>
</body>
</html>


