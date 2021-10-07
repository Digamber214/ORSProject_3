<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.FacultyCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty View</title>
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
	margin-top:5px;
	margin-bottom:5px;
	max-height: 25px;
}
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	 padding-bottom: 11px; 
	 background-color: #ebebe0;
}


.hm-gradient {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg.png');
	
}
</style>
</head>
<body class="hm-gradient">
	<div class="header">
		<%@include file="Header.jsp"%>
	</div>
	<div>
		<jsp:useBean id="dto" class="in.co.rays.dto.FacultyDTO"
			scope="request"></jsp:useBean>
		<main>
		<form action="<%=ORSView.FACULTY_CTL%>" method="post">
			<%
				List ll = (List) request.getAttribute("collegeList");
				List lli = (List) request.getAttribute("courseList");
				List llist = (List) request.getAttribute("subjectList");
			%>
			<div class="row log1">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-body">
							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (id > 0) {
							%>
							<h3 class="text-center default-text py-3">UPDATE FACULTY</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text py-3">ADD FACULTY</h3>
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
								<span class="pl-sm-5"><b>First Name</b><span
									style="color: red;">*</span></span> </br>
								<i class="fas fa-user-alt prefix grey-text fa-5x css" style="font-size: 1rem;"></i> <input
									type="text" name="firstName" id="defaultForm-email"
									placeholder="Enter First Name"
									style="border: 2px solid #8080803b;"
									value="<%=DataUtility.getStringData(dto.getFirstName())%>">
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("firstName", request)%></font><br>

								<span class="pl-sm-5"><b>Last Name</b><span
									style="color: red;">*</span></span> </br> <i
									class="fas fa-user-circle prefix grey-text fa-5x css" style="font-size: 1rem;"></i> <input
									type="text" name="lastName" id="defaultForm-email"
									placeholder="Enter Last Name"
									style="border: 2px solid #8080803b;"
									value="<%=DataUtility.getStringData(dto.getLastName())%>">
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("lastName", request)%></font><br>

								<span class="pl-sm-5"><b>Email Id</b><span
									style="color: red;">*</span></span> </br> <i
									class="fas fa-envelope prefix grey-text fa-5x css" style="font-size: 1rem;"></i> <input
									type="text" name="emailId" id="defaultForm-email"
									placeholder="Enter email Id"
									style="border: 2px solid #8080803b;"
									value="<%=DataUtility.getStringData(dto.getLogin())%>">
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("login", request)%></font><br>

								<span class="pl-sm-5"><b>Qualification</b><span
									style="color: red;">*</span></span> </br> <i
									class="fas fa-user-tie prefix grey-text fa-5x css" style="font-size: 1rem;"></i> <input
									type="text" name="qualification" id="defaultForm-email"
									placeholder="Enter Qualification"
									style="border: 2px solid #8080803b;"
									value="<%=DataUtility.getStringData(dto.getQualification())%>">
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("qualification", request)%></font><br>

								<span class="pl-sm-5"><b>Mobile No</b><span
									style="color: red;">*</span></span> </br> <i
									class="fa fa-phone prefix grey-text fa-5x css" style="font-size: 1rem;"></i> <input type="text"
									name="mobileNo" id="defaultForm-email"
									placeholder="Enter mobile No" maxlength="10"
									style="border: 2px solid #8080803b;"
									value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font><br>

								<span class="pl-sm-5"><b>Gender</b><span
									style="color: red;">*</span></span> </br> <i
									class="fa fa-venus-mars prefix grey-text fa-5x css" style="font-size: 1rem;"></i>
								<div style="margin-left: 47px;">
									<%
										HashMap map = new HashMap();
										map.put("Male", "Male");
										map.put("Female", "Female");

										String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
									%>
									<%=htmlList%></div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("gender", request)%></font><br>

								<span class="pl-sm-5"><b>DOB</b><span style="color: red;">*</span></span>
								</br> <i class="fa fa-calendar prefix grey-text fa-5x css"
									style="font-size: 1rem;"></i> <input type="date" name="dob"
									style="border: 2px solid #8080803b;" style="margin-top: -30px;"
									placeholder=" enter Date Of Birth"  onkeydown="return false"
									value="<%=DataUtility.getDateString(dto.getDob())%>"> <font
									color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("dob", request)%></font><br>

								<span class="pl-sm-5"><b>College</b><span
									style="color: red;">*</span></span> </br> <i
									class="fas fa-building prefix grey-text fa-5x css" style="font-size: 1rem;"></i>
								<div style="margin-left: 47px;">
									<%=HTMLUtility.getList("collegeName", String.valueOf(dto.getCollegeId()), ll)%>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("collegeName", request)%></font><br>

								<span class="pl-sm-5"><b>Subject</b><span
									style="color: red;">*</span></span> </br> <i
									class="fa fa-book prefix grey-text fa-5x css" style="font-size: 1rem;" ></i>
								<div style="margin-left: 47px;">
									<%=HTMLUtility.getList("subjectName", String.valueOf(dto.getSubjectId()), llist)%>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("subjectName", request)%></font><br>

								<span class="pl-sm-5"><b>Course</b><span
									style="color: red;">*</span></span> </br> <i
									class="fa fa-user-md prefix grey-text fa-5x css" style="font-size: 1rem;"></i>
								<div style="margin-left: 47px;">
									<%=HTMLUtility.getList("courseName", String.valueOf(dto.getCourseId()), lli)%>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("courseName", request)%></font>


								</br>
								<%
									if (id > 0) {
								%>
								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=FacultyCtl.OP_UPDATE%>"> <input
										type="submit" name="operation" class="btn btn-success btn-md"
										style="font-size: 17px" value="<%=FacultyCtl.OP_CANCEL%>">
								</div>
								<%
									} else {
								%>
								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=FacultyCtl.OP_SAVE%>"> <input type="submit"
										name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px" value="<%=FacultyCtl.OP_RESET%>">
								</div>
								<%
									}
								%>
							</div>
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