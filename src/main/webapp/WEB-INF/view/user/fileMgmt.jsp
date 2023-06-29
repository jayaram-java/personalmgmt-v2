<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Files</title>

<%@ include file="primary.jsp"%>

<link rel="stylesheet" href="resources/css/secondary.css">

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

.AClass{
    right:0px;
    position: absolute;
}


</style>

<script type="text/javascript" src="resources/js/fileMgmt.js"></script>

<body>

	<%-- <section id=header> <%@ include file="header.jsp"%>

	<br>

	</section> --%>

	<section id="main-menu"> <%@ include file="navigate.jsp"%>

	</section>

	<section id="main-body">

	<div align="left">

		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Personal</a></li>
			<li>File Mgmt</li>
		</ul>

	</div>
	<div align="right">
		<input type="button" class="button" value="Add File"
			id="addfiles">&nbsp &nbsp &nbsp &nbsp
	</div>
	<br>
	<br>
	<div>
		<table id="filemgmtdataTable">
			<thead>
				<tr>
					<th>id</th>
					<th>File Name</th>
					<th>File Category</th>
					<th>Created Date</th>
					<!-- <th>Download</th> -->
					<th>view</th>
					<th>Open</th>
				</tr>
			</thead>
		</table>
	</div>

	<div align="right">

		<!-- <select name="email" id="email">
			<option value="">Select the correct email id</option>
			<option value="jayaramp51096@gmail.com">jayaramp51096@gmail.com</option>
			<option value="priya31998@gmail.com">priya31998@gmail.com</option>

		</select> --> <!-- <input type="button" class="button" value="Sending Email"
			id="emailsent">&nbsp &nbsp &nbsp &nbsp <input type="button"
			class="button" value="PDF" id="pdfcreate"> &nbsp &nbsp &nbsp
		&nbsp -->

	</div>


	<br>
	<br>
	<br>

	<div class="form-popup" id="myForm" align="center" style="width:45%">
		<form>
			<div class="container">
			
				<h1 align="left">Files</h1>
				<hr>
				<div class="row">
					<div class="column" style="text-align: left">
						<label for="filename"><b>Name *</b></label> 
						 <br><br>
						<input style="width:240px; align : left" type="text"
							placeholder="File name" name="filename"
							id="filename" required>
					</div>
					<div class="column" style="text-align: left">
						<label for="parent"><b>Category *</b></label><br>
						<br> <select class="ui search dropdown" name="filecategory" id="filecategory">
							<option value="">Select</option>
							<option value="Government">Government</option>
							<option value="Education">Education</option>
							<option value="Personal">Personal</option>
							
						</select>

					</div>
					
			<!-- 		<select class="ui search dropdown">
  <option value="">State</option>
  <option value="AL">Alabama</option>
  <option value="AK">Alaska</option>
  <option value="AZ">Arizona</option>
  <option value="VA">Virginia</option>
  <option value="WA">Washington</option>
  <option value="WV">West Virginia</option>
  <option value="WI">Wisconsin</option>
  <option value="WY">Wyoming</option>
</select> -->
					
					
					  <div class="column" style="text-align: left">
                   
                    <label for="remarks"><b> Upload Single File *</b></label>
                     <br><br>
					  <!--  <form id="singleUploadForm" name="singleUploadForm"> -->
                        <input id="singleFileUploadInput" type="file" name="file" class="file-input" required />
                       
                   
                    </div>
                    
                    <div class="column" style="text-align: left">
						<label for="remarks"><b> Remarks </b></label> 
						 <br><br>
						<input style="width:240px"
							type="text" placeholder="Enter remarks"
							name="remarks" id="remarks" required>
					</div>
					
				</div>

				<div align="left">
					<label for="phone"><b>* Indicates mandatory fields</b></label>
				</div>
				<hr>
				<div class="row">
					<div class="column">
						<input style="width:240px" type="button" class="registerbtn" value="Save"
							id="savefile" />
					</div>

					<div class="column">
						<button style="width:240px" type="button" class="registerbtn" onClick="refreshPage()">Close</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	
   
	
	<div class="form-popup" id="myFormnxt" align="center" style="top: 10%; left:30%; width:40%;height: 87%;background: lightblue;">
	 <button onClick="refreshPage()" class="close AClass">
           <span>&times;</span>
        </button>
		<form>
			<div >
				<h1 align="center">preview</h1>
				<hr>
				<div class="row">
					<div  style="text-align: center;justify-content: center" >
						
						<img src="data:image/png;base64, <%=session.getAttribute("baseImage")%>" width="350" height="450"/>
						
					</div>
					
				</div>
			</div>
		</form>
	</div>

	<input type="hidden" id="filemgmtid"> </section>
	
	<div id="loading"
		style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
	<img src="resources/images/loader.gif" />
	</div>
	
</body>
</html>

