<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Order Management</title>

<link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon"/>
<link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon"/>
<link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

<link href="${pageContext.request.contextPath}/views/admin/static/css/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/views/admin/static/css/font-awesome.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/views/admin/static/css/custom.css" rel="stylesheet"/>
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
<link href="${pageContext.request.contextPath}/views/admin/static/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
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
						<h2>All Orders</h2>
						<h5>You can manage oder in here</h5>


					</div>
				</div>
				<!-- /. ROW  -->
				<hr />

				<div class="row">
				
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="panel panel-default">
							<div class="panel-heading">Advanced Tables</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th>Order Details ID</th>
												<th>Order ID</th>
												<th>Buyer</th>
												<th>Email</th>
												<th>Date</th>
												<th>Product</th>
												<th>Quantity</th>
												<th>Price</th>
												<th>Discount</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${requestScope.orderDetailsList}" var="list">
												<tr class="odd gradeX">
													<td>${list.id}</td>
													<td>${list.order.id}</td>
													<td>${list.order.buyer.username}</td>
													<td>${list.order.buyer.email }</td>
													<td>${list.order.created_at}</td>
													<td>${list.product.name}</td>
													<td>${list.quantity}</td>
													<td>$ ${list.product.price}</td>
													<td>$ ${list.discount}</td>
													<td class="center">Success</td>
													<td>
<%--														<a--%>
<%--														href="<c:url value='/admin/order/edit?id=${list.id }'/>"--%>
<%--														class="center">Edit</a> |--%>
														<a
														href="<c:url value='/admin/order/delete?id=${list.id }'/>"
														class="center">Delete</a></td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>

			</div>

		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->

	<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/dataTables/jquery.dataTables.js"></script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/dataTables/dataTables.bootstrap.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>
	<script src="${pageContext.request.contextPath}/views/admin/static/js/custom.js"></script>

</body>
</html>