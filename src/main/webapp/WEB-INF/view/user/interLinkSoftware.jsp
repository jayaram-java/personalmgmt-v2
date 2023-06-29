<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inter link - Software</title>
<%@ include file="primary.jsp"%>
<%@ include file="primarysec.jsp"%>
</head>
<%@ include file="secondaryStyle.jsp"%>
<script type="text/javascript" src="resources/js/interLinkSoftware.js"></script>

<body>
	<%-- <section id=header> <%@ include  file="header.jsp"%>
	<br>
	</section> --%>
	<section id="main-menu">
	<%@ include file="navigate.jsp" %>
	</section>
<section id="main-body">
	<div align="left">
		<ul class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Work</a></li>
			<li>Inter Link</li>
		</ul>
	</div>
	<br>
	<br>
	<div>
		<table id="interLinkSoftwaredataTable">
			<thead>
				<tr>
					<th>Id</th>
					<th>Software Name</th>
					<th>Description</th>
					<th>Dev Level</th>
					<th>Link</th>
				</tr>
			</thead>
		</table>
	</div>
	<br>
	<input type="hidden" id="propertyid"> </section>
	<div id="loading"
		style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
	<img src="resources/images/loader.gif" />
	</div>
</body>