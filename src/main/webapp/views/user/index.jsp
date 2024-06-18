<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Home</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon" />
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon" />
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

    <!-- Google Fonts -->

    <!-- Vendor CSS Files -->
    <link href="<c:url value="/assets/vendor/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="<c:url value="/assets/user/css/style1.css"/>" rel="stylesheet">

</head>
<%
    String username = "";
    username = (String) session.getAttribute("username");
%>
<body>

<%@include file="/views/layouts/user/header.jsp"%>

<!-- ======= Hero Section ======= -->
<section id="hero" class="hero d-flex align-items-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 d-flex flex-column justify-content-center">
                <h1 data-aos="fade-up"><c:if test="${requestScope.name != null}">Hi <c:out value="${name}!"/></c:if><br> We offer modern solutions for growing your business</h1>
                <h2 data-aos="fade-up" data-aos-delay="400">Thank you for your confidence!</h2>
                <div data-aos="fade-up" data-aos-delay="600">
                    <div class="text-center text-lg-start">
                        <a href="${pageContext.request.contextPath}/shop?indexPage=1" class="btn-get-started scrollto d-inline-flex align-items-center justify-content-center align-self-center">
                            <span>Go shopping</span>
                            <i class="bi bi-arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 hero-img" data-aos="zoom-out" data-aos-delay="200">
                <img src="${pageContext.request.contextPath}/assets/user/img/other/hero-img.png" class="img-fluid" alt="">
            </div>
        </div>
    </div>

</section><!-- End Hero -->

    <%@include file="/views/layouts/user/footer.jsp"%>

    <!-- Vendor JS Files -->
    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Template Main JS File -->
    <script src="${pageContext.request.contextPath}/assets/user/js/main.js"></script>

</body>
</html>