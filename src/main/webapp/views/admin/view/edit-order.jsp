<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Edit Order</title>

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


                            <form role="form" action="${pageContext.request.contextPath}/admin/order/edit" method="post">
                                <label>
                                    <input name="orderD_id" value="${requestScope.orderD_id}" type="text" hidden="">
                                </label>

                                <div class="form-group">
                                    <label>Product</label>
                                    <div class="checkbox">
                                        <label>
                                            <select style="margin-left: -23px; width: 1208px;" name="product">
                                                <option value="" selected>Product</option>
                                                <c:forEach items="${requestScope.productList}" var="p">
                                                    <option value="${p.id}">${p.name}</option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="quantity">Quantity</label> <input style="margin-left: 11px;width: 1210px;" id="quantity" class="form-control" value="" name="quantity" />
                                </div>

                                <button type="submit" class="btn btn-primary">Edit</button>
                                <button type="reset" class="btn btn-warning">Reset</button>
                            </form>



























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