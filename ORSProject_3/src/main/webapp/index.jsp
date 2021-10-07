<%@page import="in.co.rays.ctl.ORSView"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Internal CSS -->
<style>
.p1 {
	padding-top: 130px;
}

.hm-gradient {

   background-image: url("<%=ORSView.APP_CONTEXT%>/img/bg.png");

    
  
  
    /* background-color: lightblue;  */
    	
}
</style>
<body class="hm-gradient">

	<!--  class selector -->
	<!-- div makes a box model and always starts from a new line  -->
	<div class="p1">
		<h1 align="center">
			<img src="img/custom.png" width="318" height="127" border="0">
		</h1>
		<h1 align="Center">
			<a href="<%=ORSView.WELCOME_CTL%>" style="color: red;"> <font
				size="8px">Online Result System</font></a>
		</h1>
	</div>
</body>
</html>
