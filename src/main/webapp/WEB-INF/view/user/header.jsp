
 <script type="text/javascript" src="resources/js/general.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script> 
<script type="text/javascript" src="http://www.givainc.com/labs/lib/jquery.marquee.js"></script>
<link type="text/css" href="http://www.givainc.com/labs/css/jquery.marquee.css" rel="stylesheet" media="all" />

<script type="text/javascript" src="resources/js/general2.js"></script> 

<!-- 
<div align="left">

	<h1>ABC Company</h1>


</div> -->

	<%
		if ((String) session.getAttribute("active") == "employee"  && (String) session.getAttribute("rolename") == "developer"   ) {
	%>

<div align="right">

	<input type="text" value="<%=session.getAttribute("Date")%>" disabled/>
	
</div>

		


<ul id="marquee" class="marquee" style="left: 21px;
    width: 1305px;
    height: 27px;"> 
  <li>  <%=session.getAttribute("Date")%> </li> 
  <li>  <%=session.getAttribute("info1")%>   </li> 
  <li>   <%=session.getAttribute("info2")%>  </li> 
  <li>  <%=session.getAttribute("info3")%>    </li> 
  <li>  <%=session.getAttribute("info4")%>   </li> 
  <li>  <%=session.getAttribute("info5")%>   </li> 
  <li>  <%=session.getAttribute("info6")%>   </li> 
  <li>  <%=session.getAttribute("info7")%>   </li> 
</ul> 


<%
		}
	%>
