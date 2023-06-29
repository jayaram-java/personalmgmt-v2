<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report - expense</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<link rel="stylesheet" href="resources/css/customselect.css">

<link rel="stylesheet" href="resources/css/button.css">

<link rel="stylesheet" href="resources/css/styleFilter.css">


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


<script type="text/javascript" src="resources/js/expenseReportFilter.js"></script>
<body>


	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	
	
	
	
	

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Report</a></li>
			<li>Expense Report</li>
		</ul>

	</div>
	
		<div class='inline'>
		
			<div class="custom-select" style="width: 168px;left: 12%;float: left;">
				<select id="year">
				<option value="">select year</option>
					<c:forEach items="${yearMaster}" var="yearMaster">
								<option value="${yearMaster.year}"
									${yearMaster == year ? 'selected' : ''}>${yearMaster.year}</option>
							</c:forEach>
				</select>
			</div>
			
				<div class="custom-select" style="width: 200px;left: 20%;float: left;">
				<select id="expenseName">
					<option value="">select category</option>
					<option value="All">All</option>
					<c:forEach items="${expenseCategory}" var="expenseCategory">
								<option value="${expenseCategory.name}"
									${expenseCategory == name ? 'selected' : ''}>${expenseCategory.name}</option>
							</c:forEach>
				</select>
	</div>
	
	 <div class="card">
      <h4> Price </h4>

      <div class="price-content">
        <div>
          <label>Min</label>
          <p id="min-value">Rs.50</p>
        </div>

        <div>
          <label>Max</label>
          <p id="max-value">Rs.500</p>
        </div>
      </div>

        <div class="range-slider">
          <input type="range" class="min-price" value="100" min="10" max="5000" step="10">
          <input type="range" class="max-price" value="500" min="10" max="5000" step="10">
        </div>
    </div>
	
	</div>
	
	<br>
	<br>

	<div>
		<table id="expensereportinfodataTable">
			<thead>
				<tr>
					<th>Expense Name</th>
					<th>Amount</th>
					<th>Description</th>
					<th>Paid through</th>
					<th>Date</th>
				</tr>
			</thead>
			  <tfoot>
            <tr>
               <!--  <th colspan="4" style="text-align:right">Total:</th> -->
               <th></th>
               <th></th>
               <th></th>
               <th></th>
              
            </tr>
        </tfoot>
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


	