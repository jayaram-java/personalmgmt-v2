<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login - Personal Mgmt</title>

<%@ include file="frontTechnology.jsp"%>
<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/css/sweetalert.min.css">
<!--  <link rel="stylesheet" href="resources/css/font-awesome.min.css"> --> 
<script src="resources/jquery/sweetalert.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
 

<style>
.toggle-password {
	position: absolute;
	top: 40%;
	right: .5rem;
	cursor: pointer;
	user-select: none;
}

.right {
	float: right;
}

.left {
	float: left;
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
</style>

<script type="text/javascript" src="resources/js/adminLogin.js"></script>
</head>
<body>
<body>

	<section id=header> <%@ include file="header.jsp"%>



	</section>

	<section id="main-menu">

	<div class="navbar"></div>

	</section>
	
	

	<section id="main-body"> <br>
	<br>
	<br>
	<br>
	
	<br>
	<br>

	<hr>

	<div align="center">



		<div class="login-page">
			<div class="form">
				<h1>User Login</h1>
				<form>
					<table style="width: 100%">
						<tr>
							<!-- <td><b>UserName</b></td> -->
							<td><input type="text" name="username" id="username"
								placeholder="username" /></td>
								
								<!-- <td><i class="fa fa-user" aria-hidden="true"></i></td> -->
						</tr>
						<tr>
							<!-- <td><b>Password</b></td> -->
							<td><input type="password" name="password" id="password"
								placeholder="password" /> <i class="far fa-eye" id="togglePassword" style="margin-left: 220px;
    margin-top: -42px; cursor: pointer;"></i></td>
							

						</tr>
					</table>
					<input type="button" style="background-color: #008CBA;"
						value="Login" id="employeelogin" />
						
						
				</form>
			</div>

		</div>
	</div>

	<!-- <div class="form-popup" id="myForm" align="center">

		<div class="container">
			<div class="row">
				<div class="col-sm-4">

					<label>Current Password</label>
					<div class="form-group pass_show">
						<input type="password" value="faisalkhan@123" class="form-control"
							placeholder="Current Password">
					</div>
					<label>New Password</label>
					<div class="form-group pass_show">
						<input type="password" value="faisal.khan@123"
							class="form-control" placeholder="New Password">
					</div>
					<label>Confirm Password</label>
					<div class="form-group pass_show">
						<input type="password" value="faisal.khan@123"
							class="form-control" placeholder="Confirm Password">
					</div>

				</div>
			</div>
		</div>
	</div> -->




	</section>

	<section id="footer"> </section>


</body>
</html>