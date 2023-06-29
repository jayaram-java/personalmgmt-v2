<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product - Master</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<link rel="stylesheet" href="resources/css/customselect.css">

<link rel="stylesheet" href="resources/css/button.css">

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


<script type="text/javascript" src="resources/js/product.js"></script>
<body>


	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	
	
	
	
	

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Master</a></li>
			<li>Product</li>
		</ul>

	</div>
	
	
	
	 <div>

		<div class='inline'>
			

		
			
	<div class="custom-select" style="width: 200px;left: 20%;float: left;">
				<select id="productcategory" name="productcategory" >
					<option value="">select category</option>
					<option value="">All</option>
					<option value="Smart Phone">Smart Phone</option>
					<option value="Laptop">Laptop</option>
					<option value="Accessories">Accessories</option>
				</select>
	</div>
	
		<div class="container1">
			<input class="button1" type="button" value="search" id="searchProducts" >
		</div>
		
	
	</div>
	


	</div>
	
 

	<br>
	<br>
	<br>

	<div>
		<table id="productdataTable">
			<thead>
				<tr>
					<th>Id</th>
					<th>Product Name</th>
					<th>price</th>
					<th>Category Name</th>
					<th>Created by</th>
					<th>Created Date</th>
				</tr>
			</thead>
			
		</table>
	</div>

	<br>
	<br>
	<br>
	
	<input type="hidden" id="propertyid"> </section>
	<div id="loading"
		style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
	<img src="resources/images/loader.gif" />
	</div>
	
</body>
</html>


	