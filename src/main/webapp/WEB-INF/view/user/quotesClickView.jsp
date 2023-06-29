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
<script type="text/javascript" src="resources/js/quotesClickView.js"></script>
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


.header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
.nav {
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:left;
    padding:5px;
}
.active {
    font-weight:bold;
}
.section {
    width:350px;
    float:left;
    padding:10px;
}
.footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;
}




</style>




<body >

	<section id=header> <%@ include  file="header.jsp"%>

	<br>

	</section>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	
	
	
	

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li>quotesClickView</li>
		</ul>

	</div>
	
	<div class="header">
     <h1>City Gallery</h1>
</div>
<div class="nav">
    <ul>
        <li><a href="#section-london">London</a></li>
        <li><a href="#section-paris">Paris</a></li>
    </ul>
</div>
<div id="section-london" class="tab-content">
    <h2>London</h2>
    <p>London is the capital city of England. It is the most populous city in the United Kingdom, with a metropolitan area of over 13 million inhabitants.</p>
</div>
<div id="section-paris" class="tab-content">
    <h2>Paris</h2>
    <p>Paris, France's capital, is a major European city and a global center for art, fashion, gastronomy and culture. Its picturesque 19th-century cityscape is crisscrossed by wide boulevards and the River Seine. </p>
</div>
<div class="footer">Footer</div>
	
</section>
</body>
</html>