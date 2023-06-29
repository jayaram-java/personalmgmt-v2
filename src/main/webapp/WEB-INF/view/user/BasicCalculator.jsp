<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Special - time</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<link rel="stylesheet" href="resources/css/dropdown.css">

<link rel="stylesheet" href="resources/css/datetimepicker.css">

<script src="resources/jquery/jquery.min.js"></script>

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">
	
	<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
	 <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js" integrity="sha512-LGXaggshOkD/at6PFNcp2V2unf9LzFq6LE+sChH7ceMTDP0g2kn6Vxwgg7wkPP7AAtX+lmPqPdxB47A0Nz0cMQ==" crossorigin="anonymous"></script> -->

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

<script type="text/javascript" src="resources/js/timeDifference.js"></script>
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
			<li>Calc</li>
		</ul>

	</div>
	
	<br>
	&nbsp;&nbsp;
	 <h3>Calculation :</h3>
		 
		 	<br>

		<div>

	&nbsp;&nbsp;&nbsp;&nbsp;
			<div class='inline'>
				<P>
					input 1: <input class="text-box" type="text" id="input1"
						placeholder="input" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

			&nbsp;&nbsp;


			<div class='inline'>
				<P>
					<select name="operation" id="operation">
						<option value="">Select</option>
						<option value="+">+</option>
						<option value="-">-</option>
						<option value="*">*</option>
						<option value="/">/</option>
					</select>
				</P>
			</div>

			&nbsp;&nbsp;

			<div class='inline'>
				<P>
					input 2: <input class="text-box" type="text" id="input2"
						placeholder="input" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');">
				</P>
			</div>

				&nbsp;&nbsp;

			<div class='inline'>
				<P>
					Result : <input class="text-box" type="text" id="calcResult"
						placeholder="result" style="width: 270px;" readonly>
				</P>
			</div>
			

		</div>
		
			<br>
	&nbsp;&nbsp;
	
	 <h3>Notes :</h3>
		 
		 	<br>

		<div>

			&nbsp;&nbsp;&nbsp;&nbsp;
			<div class='inline'>
				<P>
					<textarea id="notes" name="notes" rows="4" cols="100" style="font-size:20px">
</textarea>

				</P>
			</div>

			&nbsp;&nbsp;


		</div>


	</section>
</body>
</html>


	