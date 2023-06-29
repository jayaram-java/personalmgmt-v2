

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
	
	<a href="dashboard" class="active">Dashboard</a>
	
	<%
		if ((String) session.getAttribute("active") == "admin") {
	%>
	
	
	
	<div class="dropdown">
			<button class="dropbtn">
				Master <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="user">User</a>
					<a href="expenseCategory">Expense Category</a>
			</div>
		</div>
		
	
		
	
	
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
	