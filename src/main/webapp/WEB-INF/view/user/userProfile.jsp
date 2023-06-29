<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>

<%@ include file="primary.jsp"%>


<%@ include file="primarysec.jsp"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">


</head>

<style>

/* The popup form - hidden by default */
.form-popup1 {
  display: none;
  position: fixed;
  bottom: 160px;
  right: 244px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
 	max-width: 324px;
    padding: 50px;
    background-color: lightslategray;
}

.column1 {
    float: left;
    width: 50%;
    padding: 1px;
    height: 50px;
}

.registerbtn1 {
    background-color: blue;
    color: white;
    padding: 10px 20px;
    margin: 9px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

.registerbtn2 {
    background-color: #28a745;
    color: white;
    padding: 10px 20px;
    margin: 9px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

.toggle-password {
	position: absolute;
	top: 40%;
	right: .5rem;
	cursor: pointer;
	user-select: none;
}


</style>

<%@ include file="secondaryStyle.jsp"%>

<script type="text/javascript" src="resources/js/userProfile.js"></script>
<body>

	
	<section id="main-menu">
	<%@ include file="navigate.jsp" %>
	</section>
<section id="main-body">
	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">User</a></li>
			<li>Profile</li>
		</ul>

	</div>
	
	<div align="right">
		<input type="button" class="button" value="Change Password" id="changepassword">&nbsp
		&nbsp &nbsp &nbsp

	</div>
	
	<br>
	<br>
	<div>
		<table id="userprofiletable">
			<thead>
				<tr>
					
					<th>User Name</th>
					<th>Phone</th>
					<th>E-mail</th>
					<th>Created Date</th>
					<th>Modified Date</th>
					<th>View</th>
				</tr>
			</thead>
		</table>
	</div>
	<br>

	<div class="form-popup1" id="myForm" align="center">
		<form>

			<div class="form-container">
				<div class="row">
					<div class="col-sm-4">

						<label>Current Password *</label>
						<div class="form-group pass_show">
							<input type="text" value="" id="curpassword"
								class="form-control" placeholder="Current Password">
								<!-- <i class="far fa-eye" id="togglePassword" style="margin-left: 185px;
    margin-top: -50px; cursor: pointer;"></i> -->
						</div>
						<label>New Password *</label>
						<div class="form-group pass_show">
							<input type="text" value="" id="newpassword"
								class="form-control" placeholder="New Password">
								<!-- <i class="far fa-eye" id="togglePassword1" style="margin-left: 185px;
    margin-top: -50px; cursor: pointer;"></i> -->
						</div>
						<label>Confirm New Password *</label>
						<div class="form-group pass_show">
							<input type="text" value="" id="confirmpassword"
								class="form-control" placeholder="Confirm Password">
								<!-- <i class="far fa-eye" id="togglePassword2" style="margin-left: 185px;
    margin-top: -50px; cursor: pointer;"></i> -->
						</div>

					</div>

					<div class="row">

						<div class="column1">

							<input type="button" class="registerbtn2" value="Update"
								id="updatePassword" />
						</div>
								
						<div class="column1">
							<button type="button" class="registerbtn1" onClick="refreshPage()">Close</button>

						</div>

					</div>
				</div>
			</div>
		</form>
	</div>
	
	<div class="form-popup1" id="myForm2" align="center">
		<form>

			<div class="form-container">
				<div class="row">
					<div class="col-sm-4">

						<label>Aadhaar *</label>
						<div class="form-group pass_show">
							<input type="text"  id="aadhaarno"
								class="form-control" placeholder="aadhaarno">
				
						</div>
						<label>PAN *</label>
						<div class="form-group pass_show">
							<input type="text"  id="panno"
								class="form-control" placeholder="PAN no">
				
						</div>
						<label>UAN *</label>
						<div class="form-group pass_show">
							<input type="text"  id="uanno"
								class="form-control" placeholder="UAN no">
				
						</div>
						<label>Passport *</label>
						<div class="form-group pass_show">
							<input type="text"  id="passportno"
								class="form-control" placeholder="Passport no">
				
						</div>
						

					</div>

					<div class="row">
								
						<div class="column1">
							<button type="button" class="registerbtn1" onClick="refreshPage()">Close</button>

						</div>

					</div>
				</div>
			</div>
		</form>
	</div>
	


	<input type="hidden" id="userprofileid"> </section>
	
	<div id="loading"
		style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
	<img src="resources/images/loader.gif" />
	</div>
</body>
</html>