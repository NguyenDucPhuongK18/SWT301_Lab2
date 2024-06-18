<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Favicons -->
	<link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon" />
	<link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon" />
	<link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

	<link href="${pageContext.request.contextPath}/views/admin/static/css/bootstrap.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/views/admin/static/css/font-awesome.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/views/admin/static/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/views/admin/static/css/custom.css" rel="stylesheet" />
	<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/views/admin/view/nav-bar.jsp"/>
		<jsp:include page="/views/admin/view/slide-bar.jsp"/>
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Admin Dashboard</h2>
						<h5 style="font-weight: bolder">Welcome : ${requestScope.username}</h5>
					</div>
				</div>
				<div class="row" style="text-align: center">
					<div style="">
						<h3>
							Total User: ${requestScope.total_user }
						</h3> 
					</div>
					<div style="">
						<h3>
							Total Product: ${requestScope.total_pro }
						</h3> 
					</div>
					<div style="">
						<h3>
							Total Category: ${requestScope.total_cat }
						</h3> 
					</div>
					<div style="">
						<h3>
							Total Order Item: ${requestScope.total_orderDetails }
						</h3> 
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/morris/raphael-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/morris/morris.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/custom.js"></script>
</body>
</html>