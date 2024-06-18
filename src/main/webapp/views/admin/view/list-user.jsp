<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<c:url value="/views/admin/static" var="url"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>User Management</title>
    <!-- Favicons -->
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon"/>
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon"/>
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">
    <link href="${pageContext.request.contextPath}/views/admin/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/font-awesome.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/css/custom.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/views/admin/static/js/dataTables/dataTables.bootstrap.css"
          rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
</head>
<body>
<div id="wrapper">

    <jsp:include page="/views/admin/view/nav-bar.jsp"/>
    <jsp:include page="/views/admin/view/slide-bar.jsp"/>

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>All Users</h2>
                    <h5>You can manage user in here!</h5>
                </div>
            </div>
            <hr/>

            <div class="row">
                <div>
                    <button class="btn-primary"
                            style="width: 260px;border: none;border-radius: 3px;height: 30px;margin-bottom: 20px;"
                            onclick="window.location.href='<c:url value='/admin/user/add'/>';">New User
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
                                        <th style="text-align: center">ID</th>
                                        <th style="text-align: center">Username</th>
                                        <th style="text-align: center">Password</th>
                                        <th style="text-align: center">Email</th>
                                        <th style="text-align: center">Action</th>
                                        <th style="text-align: center">User-Type</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.userList}" var="user">
                                        <tr class="odd gradeX">
                                            <td style="text-align: center">${user.id}</td>
                                            <td style="text-align: center">${user.username}</td>
                                            <td style="text-align: center">${user.password}</td>
                                            <td style="text-align: center" class="center">
                                                <c:choose>
                                                    <c:when test="${user.role.id == 1 }">
                                                        Admin
                                                    </c:when>
                                                    <c:otherwise>
                                                        Client
                                                    </c:otherwise>
                                                </c:choose></td>
                                            <td style="text-align: center">${user.email}</td>

                                            <td style="text-align: center">

                                                    <a
                                                            href="<c:url value='/admin/user/edit?id=${user.id }'/>"
                                                            class="center">Edit
                                                    </a>

                                                <c:if test="${user.role.id != 1}">
                                                    |<a
                                                            href="<c:url value='/admin/user/delete?id=${user.id }'/>"
                                                            class="center">Delete
                                                    </a>
                                                </c:if>

                                            </td>
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
</div>

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