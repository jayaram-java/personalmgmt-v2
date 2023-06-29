<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense Category</title>

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

select {
    width: 270px;
    height: 36px;
    float: left;
    margin: 1px;
}
    select:focus {
        min-width: 270px;
        width: auto;
    }

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

.tooltip {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: black;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  
  /* Position the tooltip */
  position: absolute;
  z-index: 1;
  bottom: 100%;
  left: 50%;
  margin-left: -60px;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
}



</style>

<script type="text/javascript" src="resources/js/expenseCategory.js"></script>


<body>


	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>

	<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
			<li><a href="#">Master</a></li>
			<li>Expense Category</li>
		</ul>

	</div>

	<div align="right">


		<input type="button" class="button" value="New Category" id="newExpCat">&nbsp
		&nbsp &nbsp &nbsp



	</div>

	<br>

	<br>


	<div >
		<table id="expenseCategorydatatable" >
			<thead>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>Visibility</th>
					<th>user</th>
					<th>Edit</th>
					 
				</tr>
			</thead>
		</table>

	</div>

	<br>
	<br>
	<br>
	<br>



	<div class="form-popup" id="myForm" align="center">
		<form>
			<div class="container">
				<h1 align="left">Expense Category</h1>

				<hr>

				<div class="row">

					<div class="column">
						<label for="categoryname" style="float: left;"><b>Category Name *</b></label> <input
							type="text" placeholder="Enter Exp. Category Name" name="categoryname"
							id="categoryname" required>
					</div>
					
						<div class="column">
						<label for="categoryname" style="float: left;"><b>Description</b></label> <input
							type="text" placeholder="Enter desc" name="description"
							id="description" required>
					</div>

				

				</div>
				
					<div class="row">

					<div class="column">
						<label for="parent" style="float: left;"><b>Visibility *</b></label><br> <br> <select name="visibility"
							id="visibility">
							<option value="">Select</option>
						</select>
					</div>
					
					<div class="column">
						<label for="parent" style="float: left;"><b>User </b></label><br> <br> <select name="user"
							id="user">
							<option value="">Select</option>
						</select>
					</div>
				

					
				</div>
				
				<div align ="left">
				
				<label for="phone"><b>* Indicates mandatory fields</b></label>

				</div>
				

				
				<hr>

				<div class="row">

					<div class="column">

						<input type="button" class="registerbtn" value="Save"
							id="saveExpCatDetails" />
					</div>

					<div class="column">
						<button type="button" class="registerbtn" onClick="refreshPage()">Close</button>

					</div>

				</div>
			</div>
		</form>
	</div>
	
	</section>
	
	<input type="hidden" id="expensecategoryid"> </section>
	
		
	<div id="loading"
		style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
	<img src="resources/images/loader.gif" />
	</div>
	
	
	</body>
</html>