<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ctl.CourseCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course view</title>
<link href="<%=ORSView.APP_CONTEXT%>/css/main.css" rel="stylesheet"
	id="bootstrap-css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.1/js/mdb.min.js"></script>
<style type="text/css">
.log1 {
	padding-top: 5px;
	/* padding-left: 30%; */

}



.hm-gradient {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg.png');
}
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	 padding-bottom: 11px; 
	 background-color: #ebebe0;
}
</style>
</head>
<body class="hm-gradient">
	<div class="header">
		<%@include file="Header.jsp"%>
	</div>
	<div>
		<jsp:useBean id="dto" class="in.co.rays.dto.CourseDTO"
			scope="request"></jsp:useBean>

		<main>
		<form action="<%=ORSView.COURSE_CTL%>" method="post">

			<div class="row log1">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-body">
							<%
							  long id=DataUtility.getLong(request.getParameter("id"));
							
							
							if (id >0)  {
							%>
							<h3 class="text-center default-text py-3">UPDATE COURSE</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text py-3">ADD COURSE</h3>
							<%
								}
							%>
							<!--Body-->
							<div>


								<H6 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H6>

								<H6 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
											<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H6>

								<input type="hidden" name="id" value="<%=dto.getId()%>">
								<input type="hidden" name="createdBy"
									value="<%=dto.getCreatedBy()%>"> <input type="hidden"
									name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
									type="hidden" name="createdDatetime"
									value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
								<input type="hidden" name="modifiedDatetime"
									value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
							</div>
							<div class="md-form">
							  <span class="pl-sm-5"><b>Course Name</b><span style="color:red;">*</span></span>
								</br><i class="fa fa-book prefix grey-text css" style="font-size: 1rem;"></i> <input type="text"
									name="courseName" id="defaultForm-email"
									placeholder="Enter Course Name"
									style="border: 2px solid #8080803b;"
									value="<%=DataUtility.getStringData(dto.getCourseName())%>">
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("courseName", request)%></font></br>
								
								<span class="pl-sm-5"><b>Duration</b><span style="color:red;">*</span></span>
								</br> <i class="fa fa-clock-o prefix grey-text css" style="font-size: 1rem;"></i>
								<div style="margin-left: 47px;">

									<%
										HashMap map = new HashMap();
										map.put("1Year", "1Year");
										map.put("2Year", "2Year");
										map.put("3Year", "3Year");
										map.put("4Year", "4Year");
										map.put("5Year", "5Year");
										String HtmlList = HTMLUtility.getList("duration", dto.getDuration(), map);
									%>
									<%=HtmlList%> 
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("duration", request)%></font></br>
								
								<span class="pl-sm-5"><b>Description</b><span style="color:red;">*</span></span>
								</br> <i class="fa fa-sort-amount-desc prefix grey-text css" style="font-size: 1rem;padding-bottom: 20px; "></i>
								<textarea name="description" placeholder="Enter description"
									style="border: 2px solid #8080803b;" rows="5" cols="5"><%=DataUtility.getStringData(dto.getDescription())%></textarea>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("description", request)%></font>
							</div>
					
							<%
								if(id>0) {
							%>
							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=CourseCtl.OP_UPDATE%>"> <input type="submit"
									name="operation" class="btn btn-success btn-md"
									style="font-size: 17px" value="<%=CourseCtl.OP_CANCEL%>">
							</div>
							<%
								} else {
							%>
							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=CourseCtl.OP_SAVE%>"> <input type="submit"
									name="operation" class="btn btn-warning btn-md"
									style="font-size: 17px" value="<%=CourseCtl.OP_RESET%>">
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<div class="col-md-4 mb-4"></div>
				</div>
		</form>
		</main>


	</div>

</body>
<%@include file="Footer.jsp"%>
</html>