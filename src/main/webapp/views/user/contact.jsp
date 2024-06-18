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
    <c:if test="${sessionScope.account == null}">
        <c:redirect url="/login"/>
    </c:if>
    <%@include file="/views/layouts/user/header.jsp"%>

    <main id="main">
        <section id="contact" class="contact" style="margin-top: 6%">
            <div class="container" data-aos="fade-up">
                <header class="section-header">
                    <h2>Contact</h2>
                    <p>Contact Us</p>
                </header>
                <div class="row gy-4">
                    <div class="col-lg-6">
                        <div class="row gy-4">
                            <div class="col-md-6">
                                <div class="info-box">
                                    <i class="bi bi-geo-alt"></i>
                                    <h3>Address</h3>
                                    <p>A108 Adam Street,<br>New York, NY 535022</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info-box">
                                    <i class="bi bi-telephone"></i>
                                    <h3>Call Us</h3>
                                    <p>+1 5589 55488 55<br>+1 6678 254445 41</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info-box">
                                    <i class="bi bi-envelope"></i>
                                    <h3>Email Us</h3>
                                    <p>info@example.com<br>contact@example.com</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info-box">
                                    <i class="bi bi-clock"></i>
                                    <h3>Open Hours</h3>
                                    <p>Monday - Friday<br>9:00AM - 05:00PM</p>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="col-lg-6">
                        <form action="${pageContext.request.contextPath}/contact" method="post">
                            <div class="row gy-4">

                                <div class="col-md-6">
                                    <input type="text" name="name" class="form-control" placeholder="Your Name" required>
                                </div>

                                <div class="col-md-6 ">
                                    <input type="email" class="form-control" name="email" placeholder="Your Email" required>
                                </div>

                                <div class="col-md-12">
                                    <input type="text" class="form-control" name="subject" placeholder="Subject" required>
                                </div>

                                <div class="col-md-12">
                                    <textarea class="form-control" name="message" rows="6" placeholder="Message" required></textarea>
                                </div>

                                <h6 class="text-center text-danger">
                                    <c:if test="${requestScope.notice != null}">
                                        <c:out value="${requestScope.notice}"/>
                                    </c:if>
                                </h6>



                                <div class="col-md-12 text-center">
                                    <button class="btn btn-success" type="submit">Send Message</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <%@include file="/views/layouts/user/footer.jsp"%>

    <!-- Vendor JS Files -->

    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Template Main JS File -->
    <script src="${pageContext.request.contextPath}/assets/user/js/main.js"></script>
</body>
</html>
