<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quotes</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<script src="resources/jquery/jquery.min.js"></script>

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript"
	src="resources/jquery/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="resources/css/sweetalert.min.css">

<script src="resources/jquery/sweetalert.min.js"></script>
<script type="text/javascript" src="resources/js/quotes.js"></script>
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










<body >

<%-- 	<section id=header> <%@ include  file="header.jsp"%>

	<br>

	</section> --%>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	
	
	
	

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li>Quotes</li>
		</ul>

	</div>
	
	
	<div align="right">
		<input type="button" class="button" value="New Quotes" id="propertyb1">&nbsp
		&nbsp &nbsp &nbsp

	</div>
	<br>

	<br>
	



<h2>${quotesHead}</h2>

<ul>
<c:forEach items="${quote}" var="quote">
  <li>${quote.name}</li>
 </c:forEach>
</ul>  
</section>



<div class="form-popup" id="myForm" align="center">
		<form>
			<div class="container">
				<h1 align="left">Quotes Details</h1>

				<hr>

				

				<div class="row">

					
					<div class="column">
						<label for="description"><b> Description *</b></label> 
						<textarea id="description" name="description" rows="4" cols="50">

						</textarea>
						
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

</body>
</html>