<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit User</title>
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
						<h2>Create New Product</h2>
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
										<c:if test="${requestScope.notice != null}">
											<c:set value="${requestScope.notice}"/>
										</c:if>
										<c:set value="info" var="info"/>
										<form role="form" action="${pageContext.request.contextPath}/admin/product/add?info=${info}" method="post">
											<div class="form-group">
												<label for="name">Name</label> <input id="name" class="form-control" placeholder="Enter Product Name" name="name" />
											</div>
											<div class="form-group">
												<label for="price">Price</label> <input id="price" class="form-control" placeholder="Enter Price" type="number" name="price" />
											</div>
											<div class="form-group">
												<label for="description">Description</label> <input id="description" class="form-control" placeholder="Enter Product Description" name="des" />
											</div>
											<div class="form-group">
												<label for="discount">Discount</label> <input id="discount" class="form-control" placeholder="Enter Product Discount" name="discount" />
											</div>
											<div class="form-group">
												<label>Category</label>
												<div class="checkbox">
													<label>
														<select style="margin-left: -23px; width: 1208px;" name="category">
															<c:forEach items="${requestScope.categories}" var="c">
																<option value="${c.id}">${c.name}</option>
															</c:forEach>
														</select>
													</label>
												</div>
											</div>
											<button type="submit" class="btn btn-primary">Add</button>
											<button type="reset" class="btn btn-warning">Reset</button>
										</form>

										<c:set value="image" var="info"/>
										<form action="${pageContext.request.contextPath}/admin/product/add?info=${info}" method="POST" enctype="multipart/form-data">
											<label>
												<input name="id" value="${requestScope.user.id }" type="text" hidden="">
											</label>
											<div class="row mb-3">
												<label for="formFile" class="form-label">Profile Image</label>
												<div class="form-group">
<%--													<c:url value="/assets/user/img/user/${requestScope.user.getAvatar()}" var="urlImg1"/>--%>
<%--													<img src="${urlImg1}" style="padding-bottom: 5px; margin-bottom: 7px; width: 300px;" id="img_display" alt="Profile">--%>
													<input style="margin-left: 13px; width: 1209px;" class="form-control" name="fileImage" accept=".jpg, .png" type="file" id="formFile">
												</div>
											</div>
											<div class="text-center">
												<button type="submit" class="btn btn-primary">Save</button>
											</div>
										</form><!-- End Profile Edit Form -->

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
	<script type="text/javascript" language="javascript">
    CKEDITOR.replace('editer', {width: '700px',height: '300px'});
</script>
</body>
</html>