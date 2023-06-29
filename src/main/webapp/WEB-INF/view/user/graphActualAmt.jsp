<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Graph - Actual Amount-Month</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<!-- <link rel="stylesheet" href="resources/css/customselect.css"> -->

<script src="resources/jquery/jquery.min.js"></script>

<link rel="stylesheet" href="resources/css/chart.css">

 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="resources/jquery/highcharts.js"></script>
<script src="resources/jquery/chart/exporting.js"></script>
<script src="resources/jquery/chart/export-data.js"></script>
<script src="resources/jquery/chart/export-data.js"></script>
<script src="resources/jquery/chart/accessibility.js"></script>


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


* {
  box-sizing: border-box;
}

.slider {
  width: 690px;
  text-align: center;
  overflow: hidden;
  margin-left: 304px;
}

.slides {
  display: flex;
  
  overflow-x: auto;
  scroll-snap-type: x mandatory;
  
  
  
  scroll-behavior: smooth;
  -webkit-overflow-scrolling: touch;
  
  /*
  scroll-snap-points-x: repeat(300px);
  scroll-snap-type: mandatory;
  */
}
.slides::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}
.slides::-webkit-scrollbar-thumb {
  background: black;
  border-radius: 10px;
}
.slides::-webkit-scrollbar-track {
  background: transparent;
}
.slides > div {
  scroll-snap-align: start;
  flex-shrink: 0;
  width: 670px;
  height: 450px;
  margin-right: 50px;
  border-radius: 10px;
  background: #c0c0c0;
  transform-origin: center center;
  transform: scale(1);
  transition: transform 0.5s;
  position: relative;
  
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 100px;
}
.slides > div:target {
/*   transform: scale(0.8); */
}
.author-info {
  background: rgba(0, 0, 0, 0.75);
  color: white;
  padding: 0.75rem;
  text-align: center;
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  margin: 0;
}
.author-info a {
  color: white;
}
img {
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.slider > a {
  display: inline-flex;
  width: 1.5rem;
  height: 1.5rem;
  background: white;
  text-decoration: none;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  margin: 0 0 0.5rem 0;
  position: relative;
}
.slider > a:active {
  top: 1px;
}
.slider > a:focus {
  background: #000;
}

/* Don't need button navigation */
@supports (scroll-snap-type) {
  .slider > a {
    display: none;
  }
}
</style>

<script type="text/javascript" src="resources/js/graph.js"></script>
<body>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	<br>
	
	<section id=header> <%@ include  file="header.jsp"%>

	</section>


	<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Report</a></li>
			<li>Actual Amount-Month</li>
		</ul>

	</div>
	
	<br>

	<div class='inline'>
		&nbsp; <select id="yearForActAmt" style="width: 150px; height: 30px;">
			<option value="">select year</option>
			<c:forEach items="${yearMaster}" var="yearMaster">
				<option value="${yearMaster.year}"
					${yearMaster == year ? 'selected' : ''}>${yearMaster.year}</option>
			</c:forEach>
		</select>
	</div>

	<div style="width: 200px;margin-left: 5%;float: right;">
		
		<label for="avg"><b>sum :</b></label> 
		
		<input type="text"  name="totalAmtcur" id="totalAmtcur"  style="width: 118px; background-color: lightsalmon; font-weight: bold; height: 40px; margin-top: -1%; font-weight: bold;" disabled>
		
		</div>  
		
		<br>

<figure class="highcharts-figure">
	<div id="container"></div>
	
	</figure>


	<input type="hidden" id="graphid"> </section>
</body>
</html>


