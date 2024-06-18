<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Product Management</title>
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon"/>
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon"/>
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">
    <link href="${pageContext.request.contextPath}/views/admin/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/font-awesome.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/custom.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <link href="${pageContext.request.contextPath}/views/admin/static/js/dataTables/dataTables.bootstrap.css"
          rel="stylesheet"/>
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
                    <h2>All Products</h2>
                    <h5>You can management product in here</h5>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>

            <div class="row">

                <div>
                    <button class="btn-primary"
                            style="width: 260px;border: none;border-radius: 3px;height: 30px;margin-bottom: 20px;"
                            onclick="window.location.href='<c:url value='/admin/product/add'/>';">New Product
                    </button>
                </div>

                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">Data Tables</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover"
                                       id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Image</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Category</th>
                                        <th>Description</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:if test="${requestScope.productList == null}">
                                        <h5 class="text-center">No product in shop!</h5>
                                    </c:if>

                                    <c:forEach items="${requestScope.productList}" var="p1">
                                        <c:url value="/assets/user/img/product/${p1.getImage()}" var="imgUrl1"/>
                                        <c:choose>
                                            <c:when test="${p1.getStatus() == 0}">
                                                <c:url value="/assets/user/img/cart/span-new2.png"
                                                       var="statusUrl1"/>
                                            </c:when>
                                            <c:when test="${p1.getStatus() == 1}">
                                                <c:url value="/assets/user/img/cart/span-hot1.png"
                                                       var="statusUrl1"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="/assets/user/img/cart/span-new2.png"
                                                       var="statusUrl1"/>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:set value="${p1.getName()}" var="name1"/>
                                        <c:set value="${p1.getPrice()}" var="price1"/>
                                        <c:set value="${p1.getDescription()}" var="description1"/>


                                        <tr class="odd gradeX">
                                            <td>${p1.id}</td>
                                            <td><img height="150" width="150" src="${imgUrl1}" alt=""/></td>

                                            <td>${p1.name }</td>
                                            <td>${p1.price }</td>
                                            <td>${p1.category.name }</td>
                                            <td>${p1.description } </td>
                                            <td><a
                                                    href="<c:url value='/product/detail?id=${p1.id}'/>"
                                                    class="center">Detail</a> | <a
                                                    href="<c:url value='/admin/product/edit?id=${p1.id }'/>"
                                                    class="center">Edit</a>
                                                | <a
                                                        href="<c:url value='/admin/product/delete?id=${p1.id }'/>"
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
    $(document).ready(function () {
        $('#dataTables-example').dataTable();
    });
</script>
<script src="${pageContext.request.contextPath}/views/admin/static/js/custom.js"></script>

</body>
</html>