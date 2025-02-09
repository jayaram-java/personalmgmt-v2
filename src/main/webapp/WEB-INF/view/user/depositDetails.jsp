<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposits</title>

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


.required-field::after {
    content: "*";
    color: red;
}

.required-field1::before {
    content: "* ";
    color: red;
}


</style>


<script type="text/javascript" src="resources/js/depositDetails.js"></script>
<body>


	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Investment</a></li>
			<li>Deposits</li>
		</ul>

	</div>

	 <div>

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
				<select id="bankName">
					<option value="">select bank</option>
					<option value="All">All</option>
					<c:forEach items="${bankNames}" var="bankNames">
								<option value="${bankNames}"
									${bankNames == bankName ? 'selected' : ''}>${bankNames}</option>
							</c:forEach>
				</select>
	</div>
	
	
	&nbsp;
	<div class="container1">
			<input class="button1" type="button" value="search" id="searchDepositDetails" >
		</div>
		
		&nbsp;
		<div style="float: right;">
			<input type="button" class="button" value="Add Deposit Details" id="addDepositDetails">
		</div>

	</div>

	</div>

	<br>
	<br>
	<br>

	<div>
		<table id="depositInfoDataTable">
			<thead>
				<tr>
					<th>AccountNumber</th>
					<th>PrincipalAmt</th>
					<th>Interest</th>
					<th>InterestAmt</th>
					<th>MaturityAmount</th>
					<th>OpenDate</th>
					<th>MaturityDate</th>
				</tr>
			</thead>
			  <tfoot>
            <tr>
               <!--  <th colspan="4" style="text-align:right">Total:</th> -->
               <th></th>
               <th></th>
               <th></th>
               <th></th>
                <th></th>
              
            </tr>
        </tfoot>
		</table>
	</div>
	
			
<div class="form-popup" id="myForm" align="center">
    <form>
        <div class="container">
            <h1 align="left">Deposit Details</h1>
            <hr>
            <div class="row">
                <div class="column">
                    <label class="required-field" for="startingFrom" style="float: left;"><b>Principal Amount</b></label>
                    <input type="text" placeholder="Enter Principal Amount" name="principalAmt" id="principalAmt" required>
                </div>
                <div class="column">
                    <label class="required-field" for="fri" style="float: left;"><b>Interest Rate</b></label>
                    <input type="text" placeholder="Enter Interest Rate" name="fri" id="fri" required>
                </div>
            </div>
           	<div class="row">
					<div class="column">
						<label for="depositDate" style="float: left;"><b>Open Date *</b></label><br> <input
							type="date" id="depositDate" name="depositDate" style="float: left;width: 270px;margin: 6px;height: 46px;align: left;">
					</div>

					<div class="column">
						<label for="maturityDate" style="float: left;"><b>Maturity Date *</b></label><br> <input
							type="date" id="maturityDate" name="maturityDate" style="float: left;width: 270px;margin: 6px;height: 46px;align: left;">
					</div>

				</div>
           
             <div class="row">
            
              <div class="column">
                    <label class="required-field" for="remarks" style="float: left;"><b>Deposit Account Number</b></label>
                    <input type="text" placeholder="Enter Acct no" name="depositAccNo" id="depositAccNo" required>
                </div>
                   <div class="column">
                    <label class="required-field" for="remarks" style="float: left;"><b>Tenure</b></label>
                    <input type="text" placeholder="Enter tenure" name="tenure" id="tenure" required>
                </div>
            </div>
              
       
            <div align="left">
                <label class="required-field1" for="phone"><b>Indicates mandatory fields</b></label>
            </div>
            <hr>
            <div class="row">
                <div class="column">
                    <input type="button" class="registerbtn" value="Save" id="saveDepositDetails" />
                </div>
                <div class="column">
                    <button type="button" class="registerbtn" id="refreshPage">Close</button>
                </div>
            </div>
        </div>
    </form>
</div>
	
	
	
	
	<div align="right">
	
		<button style='font-size: 24px; background-color: gray;'>
			Deposit PDF <i class="fa fa-arrow-right" aria-hidden="true"></i>
		</button>

		&nbsp &nbsp &nbsp &nbsp 
	
		<div class="tooltip">
			<button style='font-size: 24px' id="pdfcreate">
				<i class="fa fa-file" aria-hidden="true"></i>
			</button>
			<span class="tooltiptext">View </span> &nbsp &nbsp &nbsp &nbsp
		</div>
	
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


	