<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Login</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon" />
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon" />
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">


    <link href="<c:url value="/assets/vendor/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/css/style1.css"/>" rel="stylesheet">

</head>

<%
    String alertMsg = "";
    alertMsg = (String) request.getAttribute("alert");
    if (alertMsg == null) {
        alertMsg = "";
    }
%>



<body>

<%@include file="/views/layouts/user/header.jsp"%>

    <main>
        <div class="container">
            <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="pt-4 pb-2">
                                        <h5 class="card-title text-center pb-0 fs-4">Login to Your Account</h5>
                                        <p class="text-center small">Enter your username & password to login</p>
                                    </div>
                                    <form class="row g-3 needs-validation" action="login" method="POST" novalidate>
                                        <div class="col-12">
                                            <label for="yourUsername" class="form-label">Username</label>
                                            <div class="input-group has-validation">
                                                <input type="text" name="username" class="form-control" id="yourUsername" required>
                                                <div class="invalid-feedback">Please enter your username.</div>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="yourPassword" class="form-label">Password</label>
                                            <input type="password" name="password" class="form-control" id="yourPassword" required>
                                            <div class="invalid-feedback">Please enter your password!</div>
                                        </div>

                                        <div class="col-12">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="remember" value="true" id="rememberMe">
                                                <label class="form-check-label" for="rememberMe">Remember me</label>
                                            </div>
                                        </div>
                                        <h6 style="color: red"><%=alertMsg%></h6>
                                        <h6 style="color: red">
                                            <c:if test="${requestScope.msgAlert != null}">
                                                <c:out value="${requestScope.msgAlert}"/>
                                            </c:if>
                                        </h6>
                                        <div class="col-12">
                                            <button class="btn btn-primary w-100" type="submit">Login</button>
                                        </div>
                                        <div class="col-12">
                                            <p class="small mb-0">Don't have account? <a href="${pageContext.request.contextPath}/register">Create an account</a></p>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main><!-- End #main -->
<%@include file="/views/layouts/user/footer.jsp"%>

<!-- Vendor JS Files -->

<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/assets/user/js/main.js"></script>
</body>
</html>
