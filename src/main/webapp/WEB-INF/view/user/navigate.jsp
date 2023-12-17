

<head>

 <link rel="stylesheet" href="resources/css/font-awesome2.min.css">

<style>
body {
	margin: 0;
	font-family: Arial
}

.topnav {
	overflow: hidden;
    background-color: #333;
    top: 0;
    width: 100%;
}

.topnav a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.active {
	background-color: #04AA6D;
	color: white;
}

.topnav .icon {
	display: none;
}

.dropdown {
	float: left;
	overflow: hidden;
}

.dropdown .dropbtn {
	font-size: 17px;
	border: none;
	outline: none;
	color: white;
	padding: 14px 16px;
	background-color: inherit;
	font-family: inherit;
	margin: 0;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	float: none;
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}

.topnav a:hover, .dropdown:hover .dropbtn {
	background-color: #555;
	color: white;
}

.dropdown-content a:hover {
	background-color: #ddd;
	color: black;
}

.dropdown:hover .dropdown-content {
	display: block;
}

@media screen and (max-width: 600px) {
	.topnav a:not (:first-child ), .dropdown .dropbtn {
		display: none;
	}
	.topnav a.icon {
		float: right;
		display: block;
	}
}

@media screen and (max-width: 600px) {
	.topnav.responsive {
		position: relative;
	}
	.topnav.responsive .icon {
		position: absolute;
		right: 0;
		top: 0;
	}
	.topnav.responsive a {
		float: none;
		display: block;
		text-align: left;
	}
	.topnav.responsive .dropdown {
		float: none;
	}
	.topnav.responsive .dropdown-content {
		position: relative;
	}
	.topnav.responsive .dropdown .dropbtn {
		display: block;
		width: 100%;
		text-align: left;
	}
}
</style>




	<div class="topnav" id="myTopnav">
	
	<%
		if ((String) session.getAttribute("active") == "admin") {
	%>
	
	<a href="user" class="active" >Employee</a>
	
	<%
		}
	%>
	
	<a href="dashboard" class="active">Dashboard</a>
	
	<%
		if ((String)session.getAttribute("rolename") == "user" ) {
	%>
	
	
	<div class="dropdown">
			<button class="dropbtn">
				Transaction <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="expense">Expense</a>
			</div>
		</div>
		
		<div class="dropdown">
			<button class="dropbtn">
				Work <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="task"> Task</a>
				<a href="timesheet">Time Sheet</a>
				<a href="interLinkSoftware">InterLink-SW</a>
				<a href="websitelink">WebSite Link</a>
				
			</div>
		</div>
		
		 <div class="dropdown">
			<button class="dropbtn">
				Personal <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="checklist">Check List</a>
				<a href="property">Property</a>
				<a href="personalinfo">Personal Info</a>
				<a href="neighbourinfo">Colleague List</a>
				<a href="filesmgmt">File Mgmt</a>
				<a href="moveforward">Move Forward</a>
				<a href="keyNotes">Key Notes</a>
			</div>
		</div> 
		
		<div class="dropdown">
			<button class="dropbtn">
				Investment <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="deposits">Deposit Details</a>
			</div>
		</div>
	
		
			<div class="dropdown">
			<button class="dropbtn">
				Special <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="timeDifference">Time is gold</a>
				<a href="discountCalc">Discount Calc</a>
				<a href="basicCalculator">Calc</a>
				<a href="conversion">Convert</a>
				
			</div>
				
		</div>
		
		<div class="dropdown">
			<button class="dropbtn">
				Report <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="expensereport">Expense Report</a>
				<a href="expenseReportPagination">Expense Report - Page</a>
				<a href="expensereportviaprice">Expense Report - filter</a>
			</div>
		</div>
		
		<div class="dropdown">
			<button class="dropbtn">
				Graph <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="last7day">Last 7-Day</a>
				<a href="graphActualAmt">Actual Amount-Month</a>
				<a href="graphAvgAmt">Average Amount-Month</a>
				<a href="graphExpenseCategory">Expense category(pie chart)-Month</a>
				<a href="graphPaymentMode">Payment mode -Month</a>
				<a href="graphBalanceSheet">Balance Sheet-Month</a>
				<a href="graphFinancialYear">Financial year</a>
			</div>
		</div>
		
	
	 <%
			}
		%>
		
		<%
		if ((String) session.getAttribute("active") == "employee"  && (String) session.getAttribute("rolename") == "developer"   ) {
	%>

	<a href="multiLanguage">Multi Language</a> <a href="quotes">Quotes
		First</a> <a href="quotesClickView">Quotes Click View</a> <a
		href="developing">Developing</a> <a href="nxtcompany">Next
		Company</a> <a href="filesmgmt">File Mgmt</a>




	<%
		}
	%>
	
	

		<%
		if ((String) session.getAttribute("active") == "admin") {
	%>


		<li style="float: right"><a href="admin">Logout</a></li>

		<%
		}
	%>
		<%
		if ((String) session.getAttribute("active") == "employee") {
	%>


		<li style="float: right"><a href="home">Logout</a></li>

		<%
		}
	%>


	<div class="dropdown" style="float: right;">
		<button class="dropbtn">
			Hello,
			<%=(String) session.getAttribute("username")%>
			<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="userProfile">Profile</a>
		</div>
	</div>



</div>
	

	
	
	</head>
	