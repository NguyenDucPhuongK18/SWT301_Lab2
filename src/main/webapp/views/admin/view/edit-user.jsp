<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit User</title>
<!-- Favicons -->
<link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon"/>
<link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon"/>
<link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">
<link href="${pageContext.request.contextPath}/views/admin/static/css/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/views/admin/static/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/views/admin/static/css/custom.css" rel="stylesheet" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/views/admin/view/nav-bar.jsp"/>
		<jsp:include page="/views/admin/view/slide-bar.jsp"/>

		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Edit User</h2>
						<h5>You can edit info user in here</h5>
					</div>
				</div>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div class="col-md-12">
						<!-- Form Elements -->
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="row">
									<div class="" style="padding: 28px;">
										<c:set value="image" var="info"/>
										<form action="${pageContext.request.contextPath}/admin/user/edit?info=${info}" method="POST" enctype="multipart/form-data">
											<label>
												<input name="id" value="${requestScope.user.id }" type="text" hidden="">
											</label>
											<div class="row mb-3">
												<label for="formFile" class="form-label">Profile Image</label>
												<div class="form-group">
													<c:url value="/assets/user/img/user/${requestScope.user.getAvatar()}" var="urlImg1"/>
													<img src="${urlImg1}" style="padding-bottom: 5px; margin-bottom: 7px; width: 300px;" id="img_display" alt="Profile">
													<input style="margin-left: 15px; width: 1203px;" class="form-control" name="fileImage" accept=".jpg, .png" type="file" id="formFile">
												</div>
											</div>
											<div class="text-center">
												<button type="submit" class="btn btn-primary">Save Changes</button>
											</div>
										</form><!-- End Profile Edit Form -->

										<c:set value="info" var="info"/>
										<form role="form" action="${pageContext.request.contextPath}/admin/user/edit?info=${info}" method="post">
											<label>
												<input name="id" value="${requestScope.user.id }" type="text" hidden="">
											</label>

											<div class="form-group">
												<label for="full_name">Full Name</label> <input id="full_name" class="form-control"
											   		value="${requestScope.user.full_name}" name="full_name" />
											</div>
											<div class="form-group">
												<label for="username">User Name</label> <input id="username" class="form-control"
													value="${requestScope.user.username }" name="username" />
											</div>

											<div class="form-group">
												<label for="phone">Phone</label> <input id="phone" class="form-control"
												   value="${requestScope.user.phone }" name="phone" />
											</div>
											<div class="form-group">
												<label for="address">Address</label> <input id="address" class="form-control"
													value="${requestScope.user.address }" name="address" />
											</div>
											<div class="form-group">
												<label for="password">Password</label> <input id="password" class="form-control"
													value="${requestScope.user.password }" type="password" name="password" />
											</div>
											<div class="form-group">
												<label for="email">Email</label> <input id="email" class="form-control"
													value="${requestScope.user.email}" name="email" />
											</div>

<%--											<div class="form-group">--%>
<%--												<label>Role</label>--%>
<%--												<div class="checkbox">--%>
<%--													<label> <input type="radio" value="1" name="role" />Admin--%>
<%--													</label> <br> <label> <input type="radio" value="2"--%>
<%--														name="role" checked="checked"/>Client--%>
<%--													</label>--%>
<%--												</div>--%>
<%--											</div>--%>

											<button type="submit" class="btn btn-primary">Edit</button>
											<button type="reset" class="btn btn-warning">Reset</button>
										</form>

									</div>
								</div>
							</div>
						</div>
						<!-- End Form Elements -->
					</div>
				</div>
				<!-- /. ROW  -->
				<div class="row">
					<div class="col-md-12"></div>
				</div>
				<!-- /. ROW  -->
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->

	<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/custom.js"></script>

</body>
</html>