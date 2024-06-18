<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Create new user</title>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/font-awesome.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/custom.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans'
          rel='stylesheet' type='text/css'/>
</head>
<body>
<div id="wrapper">
    <jsp:include page="/views/admin/view/nav-bar.jsp"/>
    <jsp:include page="/views/admin/view/slide-bar.jsp"/>
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Create New User</h2>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="" style="padding: 28px;">

                                    <form role="form" action="${pageContext.request.contextPath}/admin/user/add" method="post">
                                        <div class="form-group">
                                            <label for="username">Username</label> <input id="username" class="form-control"
                                                                           placeholder="Enter username"
                                                                           name="username"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Password</label> <input id="password" class="form-control"
                                                                           placeholder="Enter password" type="password"
                                                                           name="password"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="fullName">Full Name</label> <input id="fullName" class="form-control"
                                                                        placeholder="Enter full name" name="fullName"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="phone">Phone</label> <input id="phone" class="form-control"
                                                                                           placeholder="Enter phone" name="phone"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="address">Address</label> <input id="address" class="form-control"
                                                                                        placeholder="Enter address" name="address"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="email">Email</label> <input id="email" class="form-control"
                                                                                    placeholder="Enter email" name="email"/>
                                        </div>

                                        <div class="form-group">
                                            <label>Gender</label> <br>
                                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="Male" checked/>
                                            <label class="form-check-label" for="inlineRadio1">Male</label>
                                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="Female" />
                                            <label class="form-check-label" for="inlineRadio2">Female</label>
                                        </div>

<%--                                        <div class="form-group">--%>
<%--                                            <label>User-Type</label>--%>
<%--                                            <div class="checkbox" style="margin-bottom: 30px;">--%>
<%--                                                <label><input type="radio" value="1" name="role"/>Admin User</label>--%>
<%--                                                <label><input type="radio" value="2" name="role" checked/>Client User</label>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
                                        <button type="submit" class="btn btn-primary">Create</button>
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

<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/views/admin/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/views/admin/static/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/views/admin/static/js/custom.js"></script>

</body>
</html>