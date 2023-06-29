<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discount Calc</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<link rel="stylesheet" href="resources/css/dropdown.css">

<link rel="stylesheet" href="resources/css/datetimepicker.css">

<script src="resources/jquery/jquery.min.js"></script>

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript"
	src="resources/jquery/moment-with-locales.min.js"></script>


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

.inline {
	display: inline-block;
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

<script type="text/javascript" src="resources/js/datetimepicker.js"></script>

<script type="text/javascript" src="resources/js/discountCalc.js"></script>
<body>


	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
		<br>
	
	<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Special</a></li>
			<li>Discount Calc</li>
		</ul>

	</div>
	
	<br>
	&nbsp;&nbsp;
	 <h3>Value Compare :</h3>
		 
		 	<br>

		<div>

	&nbsp;&nbsp;&nbsp;&nbsp;
			<div class='inline'>
				<P>
					Amount : <input class="text-box" type="text" id="currentAmount"
						placeholder="amount" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

			&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Compare with : <input class="text-box" type="text" id="compareWith"
						placeholder="Compare with" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

				&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Result : <input class="text-box" type="text" id="compareWithResult"
						placeholder="result" style="width: 270px;" readonly>
				</P>
			</div>
			

		</div>
		
			<br>
	&nbsp;&nbsp;
	 <h3>Get Original Price :</h3>
		 
		 	<br>

		<div>

	&nbsp;&nbsp;&nbsp;&nbsp;
			<div class='inline'>
				<P>
					Selling Price : <input class="text-box" type="text" id="sellingprice"
						placeholder="selling price" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

			&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Discount : <input class="text-box" type="text" id="discount"
						placeholder="discount" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

				&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Result : <input class="text-box" type="text" id="originalPrice"
						placeholder="result" style="width: 270px;" readonly>
				</P>
			</div>
			

		</div>
		
		
			<br>
	&nbsp;&nbsp;
	 <h3>Get Selling price :</h3>
		 
		 	<br>

		<div>

	&nbsp;&nbsp;&nbsp;&nbsp;
			<div class='inline'>
				<P>
					Original Price : <input class="text-box" type="text" id="originalpricetwo"
						placeholder="original price" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

			&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Discount : <input class="text-box" type="text" id="discounttwo"
						placeholder="discount" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

				&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Result : <input class="text-box" type="text" id="resultsellingPrice"
						placeholder="result" style="width: 270px;" readonly>
				</P>
			</div>
			

		</div>
		
			<br>
	&nbsp;&nbsp;
	 <h3>Discount :</h3>
		 
		 	<br>

		<div>

	&nbsp;&nbsp;&nbsp;&nbsp;
			<div class='inline'>
				<P>
					Original Price : <input class="text-box" type="text" id="originalpricethree"
						placeholder="original price" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

			&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Selling Price : <input class="text-box" type="text" id="sellingpricethree"
						placeholder="selling price" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

				&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Result : <input class="text-box" type="text" id="resultdiscount"
						placeholder="result" style="width: 270px;" readonly>
				</P>
			</div>
			

		</div>
		

	</section>
</body>
</html>