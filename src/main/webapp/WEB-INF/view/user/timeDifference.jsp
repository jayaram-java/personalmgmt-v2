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
			<li>Time is gold</li>
		</ul>

	</div>
	
	<br>
	 <h3>Time Duration :</h3>
		<div>
		&nbsp;&nbsp;
			<div class='inline'>
				<div style="width: 250px; margin: 50px auto;">
					<P>Start :
					 <input id="startDate" type="datetime-local" name="startDate">
					</P>
				</div>
			</div>

			&nbsp;&nbsp;

			<div class='inline'>
				<div style="width: 250px; margin: 50px auto;">
					<P>End :
					 <input id="endDate" type="datetime-local" name="endDate">
					</P>

				</div>
			</div>
			
			&nbsp;&nbsp;
			
			<div class='inline'>
				<div style="width: 50px; margin: 50px auto;">
					<P> 
					  <button type="submit" id="differenceCalculate" value="Execute">Execute</button>
					</P>

				</div>
			</div>
			
			&nbsp;&nbsp;
			
			<div class='inline'>
				<div style="width: 250px; margin: 50px auto;">
					<P> Result :
					<input type="text" id="result13" value="" disabled />
					</P>

				</div>
			</div>
			
			&nbsp;&nbsp;
			
			<div class='inline'>
				<div style="width: 250px; margin: 50px auto;">
					<P> Result in days :
					<input type="text" id="result14" value="" disabled />
					</P>

				</div>
			</div>
			
		</div>

		
	
		 <h3>Country Time zone :</h3>
		 
		 	<br>
		 
		 <div>
		<div class='inline'>
		<div class="dropdown1">
			<input class="text-box" type="text"  placeholder="Select Time Zone" readonly>
			<div class="options">
			<div  onclick="show('America/New_York')">America/New_York</div>
				<div onclick="show('Asia/Shanghai')">Asia/Shanghai</div>
				<div onclick="show('Europe/London')">Europe/London</div>
				<div onclick="show('Europe/Moscow')">Europe/Moscow</div>
			</div>
		</div>
		
		</div>
		&nbsp;&nbsp;
		
		<div class='inline'>
			<P> Country Time :
			<input class="text-box" type="text" id="countryTime" placeholder="Country time" readonly>
			</P>
		</div>
		
		&nbsp;&nbsp;
		
		<div class='inline'>
			<P> Time Diff with India :
			<input class="text-box" type="text" id="timeDiffwith" placeholder="Time Diff" readonly>
			</P>
		</div>
		

		</div>







	</section>
</body>
</html>


	