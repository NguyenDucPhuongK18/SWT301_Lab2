<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Create Category</title>

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
						<h2>Create New Category</h2>
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
										<form role="form" action="${pageContext.request.contextPath}/admin/category/add"  method="post">
											<div class="form-group">
												<label for="name">Category Name:</label>
												<input id="name" name="cate_name" class="form-control" placeholder="Enter category name" />
											</div>
											<button type="submit" class="btn btn-primary">Create</button>
											<button type="reset" class="btn btn-warning" >Reset</button>
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