<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

<script src="resources/jquery/jquery.min.js"></script>

<script src="resources/jquery/fontawesome.js"></script>

<link href="resources/css/jquery.dataTables.min.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript"
	src="resources/jquery/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="resources/css/sweetalert.min.css">

<script src="resources/jquery/sweetalert.min.js"></script>


</head>
<style>

select {
    width: 270px;
    height: 36px;
    float: left;
    margin: 1px;
}
    select:focus {
        min-width: 270px;
        width: auto;
    }

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
	height: 1500px; /* Used in this example to enable scrolling */
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

<script type="text/javascript" src="resources/js/expense.js"></script>
<body>

	<%-- <section id=header> <%@ include file="header.jsp"%>

	<br>

	</section> --%>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>

<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Transaction</a></li>
			<li>Expense</li>
		</ul>

	</div>

<div>

<div style="float: left;">

<label for="avg"><b>Avg based on current month :</b></label> 
	
	
		<input type="text"  name="averageExpense" id="averageExpense" style="width: 120px; background-color: lightcoral; font-weight: bold;" disabled> 
		
</div>



<div style="float: right;">

<input type="button" class="button" value="New Expense" id="expenseb">
</div>


</div>

<!-- 	<div align="right">

	<label for="avg"><b>Avg based on current month :</b></label>  &nbsp
	
	
		<input type="text"  name="averageExpense" id="averageExpense" style="width: 120px; background-color: lightcoral;" disabled>&nbsp &nbsp 
		<input type="button" class="button" value="New Expense" id="expenseb">&nbsp
		&nbsp &nbsp &nbsp
</div> -->
	
	<br>

	<br>

	<div>
		<table id="expensetable">
			<thead>
				<tr>
				<!-- 	<th>Id</th> -->
				 	<th>Expense-id</th> 
					<th>Name</th>
					<th>Expense description</th>
					<th>Expense Date</th>
					<th>Amount</th>
				 	<th>Month</th> 
					<th>Category</th>
					<th>created date</th>
					<th>modified date</th>
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
                <th></th>
                <th></th>
            </tr>
        </tfoot>
       
		</table>

	</div>

	<div align="right">
	
		<button style='font-size: 24px; background-color: gray;'>
			Expense PDF <i class="fa fa-arrow-right" aria-hidden="true"></i>
		</button>

		&nbsp &nbsp &nbsp &nbsp
		<div class="tooltip">
			<button style='font-size: 24px' id="emailsent">
				<i class="fa fa-envelope" aria-hidden="true"></i>
			</button>
			<span class="tooltiptext">Send Email</span> &nbsp &nbsp &nbsp &nbsp
		</div>
		<div class="tooltip">
			<button style='font-size: 24px' id="pdfcreate">
				<i class="fa fa-file" aria-hidden="true"></i>
			</button>
			<span class="tooltiptext">View </span> &nbsp &nbsp &nbsp &nbsp
		</div>
		<div class="tooltip">
			<button style='font-size: 24px' id="pdfdownload">
				<i class='fas fa-file-download'></i>
			</button>
			<span class="tooltiptext">Download </span> &nbsp &nbsp &nbsp &nbsp
			&nbsp
		</div>
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
						<label class="required-field" for="startdate" style="float: left;"><b>Expense Name </b></label> <input
							type="text" placeholder="Enter expense Name" name="expensename"
							id="expensename" required>
					</div>

					<div class="column">
						<label class="required-field" for="description" style="float: left;"><b> Description </b></label> <input
							type="text" placeholder="Enter expense Description"
							name="description" id="description" required>
					</div>
				</div>

				<div class="row">
					<div class="column">
						<label class="required-field" for="date" style="float: left;"><b> Date </b></label><br> <input
							type="date" id="date" name="date" style="float: left;width: 270px;margin: 6px;height: 46px;align: left;">
					</div>
					
					<div class="column">
						<label class="required-field" for="amount" style="float: left;"><b>Amount </b></label><input type="text"
							placeholder="Enter amount" name="amount" id="amount" required>
					</div>


				</div>

				<div class="row">
					<div class="column">
						<label class="required-field" for="parent" style="float: left;"><b>Expense Category </b></label><br>
						<br> <select name="expensecategory" id="expensecategory">
							<option value="">Select</option>
							
						</select>

					</div>
					
					<div class="column">
						<label class="required-field" for="parent" style="float: left;"><b>Payment method </b></label><br>
						<br> <select name="paymentMethod" id="paymentMethod">
							<option value="">Select</option>
							<option value="cash">Cash</option>
							<option value="online payment">Online Payment</option>
							<option value="card">Card</option>
							
						</select>

					</div>

				</div>



				<div align="left">

					<label class="required-field1" for="phone"><b>Indicates mandatory fields</b></label>

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
 <!-- <div id="loader" class="lds-dual-ring display-none overlay"></div> -->
	<input type="hidden" id="expenseid"> </section>
	
	
	<div id="loading"
		style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
	<img src="resources/images/loader.gif" />
	</div>
	
</body>
</html>


