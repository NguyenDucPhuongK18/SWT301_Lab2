<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Contact</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon" />
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon" />
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">


    <link href="<c:url value="/assets/vendor/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/css/style1.css"/>" rel="stylesheet">

</head>

<body>

<%@include file="/views/layouts/user/header.jsp"%>

    <main  style="margin-top: 5%">
        <div class="container">

            <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                            <div class="d-flex justify-content-center py-4">
                                <a href="${pageContext.request.contextPath}/home" class="logo d-flex align-items-center w-auto">
                                    <span class="d-none d-lg-block fw-bold">Home</span>
                                </a>
                            </div><!-- End Logo -->

                            <div class="card mb-3">

                                <div class="card-body">

                                    <div class="pt-4 pb-2">
                                        <h5 class="card-title text-center pb-0 fs-4">Create an Account</h5>
                                        <p class="text-center small">Enter your personal details to create account</p>
                                    </div>

                                    <form class="row g-3 needs-validation" action="register" method="POST" novalidate>
                                        <div class="col-12">
                                            <label for="yourName" class="form-label">Your Full Name</label>
                                            <input type="text" name="name" class="form-control" id="yourName" required>
                                            <div class="invalid-feedback">Please, enter your name!</div>
                                        </div>

                                        <div class="col-12">
                                            <label for="yourEmail" class="form-label">Your Email</label>
                                            <input type="email" name="email" class="form-control" id="yourEmail" required>
                                            <div class="invalid-feedback">Please enter a valid Email adddress!</div>
                                        </div>

                                        <div class="col-12">
                                            <label for="yourPhone" class="form-label">Phone</label>
                                            <div class="input-group has-validation">
                                                <input type="text" name="phone" class="form-control" id="yourPhone" required>
                                                <div class="invalid-feedback">Please choose a phone.</div>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="yourAddress" class="form-label">Address</label>
                                            <div class="input-group has-validation">
                                                <input type="text" name="address" class="form-control" id="yourAddress" required>
                                                <div class="invalid-feedback">Please choose a address.</div>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="yourUsername" class="form-label">Username</label>
                                            <div class="input-group has-validation">
<%--                                                <span class="input-group-text" id="inputGroupPrepend">@</span>--%>
                                                <input type="text" name="username" class="form-control" id="yourUsername" required>
                                                <div class="invalid-feedback">Please choose a username.</div>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label for="yourPassword" class="form-label">Password</label>
                                            <input type="password" name="password" class="form-control" id="yourPassword" required>
                                            <div class="invalid-feedback">Please enter your password!</div>
                                        </div>
                                        <div class="col-12">
                                            <p>Gender </p>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="Male" checked/>
                                                <label class="form-check-label" for="inlineRadio1">Male</label>
                                            </div>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="Female" />
                                                <label class="form-check-label" for="inlineRadio2">Female</label>
                                            </div>
                                            <div class="invalid-feedback">Please enter your gender!</div>
                                        </div>

                                        <p></p>

                                        <div class="col-12">
                                            <div class="form-check">
                                                <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required>
                                                <label class="form-check-label" for="acceptTerms">I agree and accept the <a href="#">terms and conditions</a></label>
                                                <div class="invalid-feedback">You must agree before submitting.</div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <button class="btn btn-primary w-100" type="submit">Create Account</button>
                                        </div>
                                        <div class="col-12">
                                            <p class="small mb-0">Already have an account? <a href="${pageContext.request.contextPath }/login">Log in</a></p>
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
