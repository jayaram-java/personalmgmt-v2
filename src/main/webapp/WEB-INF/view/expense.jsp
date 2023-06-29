<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<script src="resources/jquery/jquery.min.js"></script>

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript"
	src="resources/jquery/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="resources/css/sweetalert.min.css">

<script src="resources/jquery/sweetalert.min.js"></script>


<script type="text/javascript"  src="resources/jquery/bootstrap.min.js"></script>


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

#errmsg
{
color: red;
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
</style>

<script type="text/javascript" src="resources/js/expense.js"></script>
<body>

	<section id=header> <%@ include file="header.jsp"%>

	<br>

	</section>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li>Expense</li>
		</ul>

	</div>
	
	<div align="left">
	
	
	<div class = "container">  
        <div class = "row">  
          <div class = "col-sm-12" align = "center">   
           <input type = "date" name = "date">   &nbsp &nbsp
           
            <input type = "date" name = "date">  
          </div> 
          
       </div>
            
        </div> 

		<!-- <div>

			<input type="date" id="startdate" name="date">&nbsp &nbsp
			
			<input type="date" id="enddate" name="date">
			
			 <button type="submit"><i class="fa fa-search"></i></button>
			

		</div> -->

	</div>


	<div align="right">


		<input type="button" class="button" value="New Expense" id="expenseb">&nbsp
		&nbsp &nbsp &nbsp



	</div>
	
	

	<br>

	<br>

	<div>
		<table id="expensetable">
			<thead>
				<tr>
					<th>Expense-id</th>
					<th>Name</th>
					<th>Expense description</th>
					<th>Date</th>
					<th>Amount</th>
					<th>Month</th>
					<th>Category</th>
					 <th>Edit</th> 
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
                <th></th>
            </tr>
        </tfoot>
       
		</table>

	</div>
	
	<div align="right">
	
	<input type="button" class="button" value="Sending Email" id="emailsent">&nbsp
		&nbsp &nbsp &nbsp


		<input type="button" class="button" value="PDF" id="pdfcreate">&nbsp
		&nbsp &nbsp &nbsp

	</div>
	
	
	<br>
	<br>
	<br>


	<div class="form-popup" id="myForm" align="center">
		<form>
			<div class="container">
				<h1 align="left">Expense</h1>

				<hr>

				<div class="row">

					<div class="column">
						<label for="startdate"><b>Expense Name *</b></label> <input
							type="text" placeholder="Enter Task Name" name="expensename"
							id="expensename" required>
					</div>

					<div class="column">
						<label for="description"><b> Description *</b></label> <input
							type="text" placeholder="Enter Task Description"
							name="description" id="description" required>
					</div>
				</div>

				<div class="row">
					<div class="column">
						<label for="date"><b> Date *</b></label><br> <input
							type="date" id="date" name="date">
					</div>
					
					<div class="column">
						<label for="amount"><b>Amount *</b></label><input type="text"
							placeholder="Enter amount" name="amount" id="amount" required>
							&nbsp;<span id="errmsg"></span>
					</div>


				</div>

				<div class="row">
					<div class="column">
						<label for="parent"><b>Expense Category *</b></label><br>
						<br> <select name="expensecategory" id="expensecategory">
							<option value="">Select</option>
							<option value="Travel">Travel</option>
							<option value="Travel">Paying Guest(PG)</option>
							<option value="Travel">Purchase</option>
							<option value="Travel">Others</option>
						</select>

					</div>

				</div>



				<div align="left">

					<label for="phone"><b>* Indicates mandatory fields</b></label>

				</div>
				<hr>



				<div class="row">

					<div class="column">

						<input type="button" class="registerbtn" value="Save"
							id="saveexpense" />


					</div>

					<div class="column">
						<button type="button" class="registerbtn" onClick="refreshPage()">Close</button>

					</div>

				</div>
			</div>


		</form>


	</div>

	<input type="hidden" id="expenseid"> </section>
</body>
</html>


