<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
</style>

<body>

<%-- 
<section id=header> <%@ include  file="header.jsp"%>

	<br>

	</section> --%>

	<section id="main-menu">

	<%@ include file="navigate.jsp" %>
	
	</section>
	
	<section id="main-body">
	
	<div style="margin-left: 85%;">
	
		<a href="?lang=en"><fmt:message key="label.lang.en" /></a> &nbsp &nbsp <a href="?lang=ta"><fmt:message key="label.lang.ta" /></a> 
		
	</div>

	<h2>
		<fmt:message key="label.hardwork" /> &nbsp &nbsp <fmt:message key="label.hope" />  &nbsp &nbsp <fmt:message key="label.discipline" />
	</h2>
<%-- 	<div>
		<span><fmt:message key="label.content" /></span>
	</div>
	<div>
		<fmt:message key="label.changeLang" />
	</div> --%>
	
	
	</section>
	
</body>
</html>
