<%@page import="in.co.rays.ctl.CourseListCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.dto.CourseDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.CourseListCtl"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course List View</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<style>
.hm-gradient {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg.png');
}

.text {
	text-align: center;
}
</style>
</head>
<body class="hm-gradient">
	<div>
		<%@include file="Header.jsp"%>
	</div>
	<div>
		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">




			<jsp:useBean id="dto" class="in.co.rays.dto.CourseDTO"
				scope="request"></jsp:useBean>
			<%
				List list1 = (List) request.getAttribute("courseList");
			%>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				List list = ServletUtility.getList(request);
				Iterator<CourseDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 style="font-size: 40px; padding-top: 20px; color: #162390;">COURSE LIST</h1>
				<center>

					<div class="row">
						<div class="col-md-4"></div>

						<%
							if (!ServletUtility.getSuccessMessage(request).equals("")) {
						%>

						<div class="col-md-4 alert alert-success alert-dismissible"
							style="background-color: #80ff80">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>
								<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
							</h4>
						</div>
						<%
							}
						%>

						<div class="col-md-4"></div>
					</div>
					<div class="row">
						<div class="col-md-4"></div>

						<%
							if (!ServletUtility.getErrorMessage(request).equals("")) {
						%>
						<div class=" col-md-4 alert alert-danger alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>
								<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
							</h4>
						</div>
						<%
							}
						%>
						<div class="col-md-4"></div>
					</div>
					<div class="row">

						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<%=HTMLUtility.getList("CourseName", String.valueOf(dto.getId()), list1)%>
						</div>
						<div class="col-sm-2">
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

						<div class="col-sm-2">
							<input type="submit" class="btn btn-primary btn-md"
								style="font-size: 15px" name="operation"
								value="<%=CourseListCtl.OP_SEARCH%>">&emsp; <input
								type="submit" class="btn btn-warning btn-md"
								style="font-size: 15px" name="operation"
								value="<%=CourseListCtl.OP_RESET%>">
						</div>

						<div class="col-sm-2"></div>
					</div>







					</br>
					<div style="margin-bottom: 20px;" class="table-responsive">
						<table class="table">
							<thead>
								<tr style="background-color: #f2c12dcc;">

									<th width="10%"><input type="checkbox" id="select_all"
										name="Select" class="text"> Select All</th>
									<th class="text">S.NO</th>
									<th class="text">Course Name</th>
									<th class="text">Duration</th>
									<th class="text">Description</th>
									<th class="text">Edit</th>
								</tr>
							</thead>
							<%
								while (it.hasNext()) {
										dto = it.next();
							%>

							<tbody>
								<tr>
									<td align="center"><input type="checkbox" class="checkbox"
										name="ids" value="<%=dto.getId()%>"></td>
									<td align="center"><%=index++%></td>
									<td align="center"><%=dto.getCourseName()%></td>
									<td align="center"><%=dto.getDuration()%></td>
									<td align="center"><%=dto.getDescription()%></td>
									<td align="center"><a href="CourseCtl?id=<%=dto.getId()%>">Edit</a></td>
								</tr>
							</tbody>
							<%
								}
							%>
						</table>


					</div>
					<table width="100%">
						<tr>
							<td><input type="submit" name="operation"
								class="btn btn-info btn-md" style="font-size: 17px"
								value="<%=CourseListCtl.OP_PREVIOUS%>"
								<%=pageNo > 1 ? "" : "disabled"%>></td>
							<td><input type="submit" name="operation"
								class="btn btn-primary btn-md" style="font-size: 17px"
								value="<%=CourseListCtl.OP_NEW%>"></td>
							<td><input type="submit" name="operation"
								class="btn btn-danger btn-md" style="font-size: 17px"
								value="<%=CourseListCtl.OP_DELETE%>"></td>

							<td align="right"><input type="submit" name="operation"
								class="btn btn-info btn-md" style="font-size: 17px"
								style="padding: 5px;" value="<%=CourseListCtl.OP_NEXT%>"
								<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
						</tr>
						<tr></tr>
					</table>
					</br>
					<%
						}
						if (list.size() == 0) {
							System.out.println("user list view list.size==0");
					%>
					<center>
						<h1 style="font-size: 40px; padding-top: 24px; color: #162390;">Course
							List</h1>
					</center>

					</br>
					<div class="row">
						<div class="col-md-4"></div>

						<%
							if (!ServletUtility.getErrorMessage(request).equals("")) {
						%>
						<div class=" col-md-4 alert alert-danger alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>
								<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
							</h4>
						</div>
						<%
							}
						%>
						<%
							if (!ServletUtility.getSuccessMessage(request).equals("")) {
						%>
						<div class=" col-md-4 alert alert-primary alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>
								<font color="red"> <%=ServletUtility.getSuccessMessage(request)%></font>
							</h4>
						</div>
						<%
							}
						%>
						<div class="col-md-4"></div>
					</div>
					 </br>
					<div style="padding-left: 48%;">
						<input type="submit" name="operation"
							class="btn btn-primary btn-md" style="font-size: 17px"
							value="<%=CourseListCtl.OP_BACK%>">
					</div>
					<%
						}
					%>
					<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
						type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>

	</div>
	<br>

</body>
<%@include file="Footer.jsp"%>

</html>