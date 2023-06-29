<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Property</title>

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


<script type="text/javascript" src="resources/js/property.js"></script>
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
			<li>Property</li>
		</ul>

	</div>

	


	<div align="right">
		<input type="button" class="button" value="New Property" id="propertyb">&nbsp
		&nbsp &nbsp &nbsp



	</div>
	<br>

	<br>

	<div>
		<table id="propertydataTable">
			<thead>
				<tr>
					<th>property-id</th>
					<th>Name</th>
					<th>count</th>
					<th>category</th>
					<th>Description</th>
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
				<h1 align="left">Property Details</h1>

				<hr>

				<div class="row">

					<div class="column">
						<label for="startdate"><b>Name *</b></label> <input type="text"
							placeholder="Enter property" name="propertyname"
							id="propertyname" required>

					</div>

					<div class="column">
						<label for="parent"><b>category *</b></label><br> <br> <select
							name="prodSKUs" id="propertycategory">
							<option value="">--Select--</option>
							<c:forEach items="${propertycategory}" var="propertycategory">
								<option value="${propertycategory.name}"
									${propertycategory == name ? 'selected' : ''}>${propertycategory.name}</option>
							</c:forEach>
						</select>
					</div>

				</div>

				<div class="row">


					<div class="column">
						<label for="count"><b>Count *</b></label><input type="text"
							placeholder="Enter count" name="count" id="count" required>
						&nbsp;<span id="errmsg"></span>
					</div>
					
					
					<div class="column">
						<label for="description"><b> Description *</b></label> <input
							type="text" placeholder="Enter Property Description"
							name="description" id="description" required>
					</div>

				</div>

				<div align="left">

					<label for="phone"><b>* Indicates mandatory fields</b></label>

				</div>
				<hr>
				<div class="row">

					<div class="column">

						<input type="button" class="registerbtn" value="Save"
							id="savePropertyDetails" />

					</div>

					<div class="column">
						<button type="button" class="registerbtn" onClick="refreshPage()">Close</button>

					</div>

				</div>
			</div>
		</form>
	</div>

	<input type="hidden" id="propertyid"> </section>
</body>
</html>


