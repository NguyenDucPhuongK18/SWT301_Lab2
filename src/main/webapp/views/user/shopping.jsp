<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Shop</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon"/>
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon"/>
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

    <!-- all css here -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/plugin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/bundle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/style2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/responsive.css">
    <script src="${pageContext.request.contextPath}/assets/user/js/modernizr-2.8.3.min.js"></script>

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="<c:url value="/assets/vendor/font-awesome/css/font-awesome.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/user/css/style1.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/css/style2.css"/>" rel="stylesheet">
</head>

<body>

<%@include file="/views/layouts/user/header.jsp" %>

<main style="margin-top: 5%" id="main">
    <!-- ======= Blog Section ======= -->
    <section id="blog" class="blog">
        <div class="container" data-aos="fade-up">
            <div class="row">
                <div class="col-lg-4">
                    <div class="sidebar">
                        <h3 class="sidebar-title">Search</h3>
                        <div class="sidebar-item search-form">
                            <form action="${pageContext.request.contextPath}/shop?indexPage=1" method="POST">
                                <label>
                                    <input name="txtSearch" style="height: 37px !important;background: white !important;" type="text">
                                </label>
                                <button type="submit"><i class="bi bi-search"></i></button>
                            </form>
                        </div>

                        <h3 class="sidebar-title">Categories</h3>
                        <div class="sidebar-item categories">
                            <ul>
                                <c:forEach items="${requestScope.categoryList}" var="category">
                                    <c:set value="${category.getName()}" var="nameCategory"/>
                                    <li>
                                        <a class="btn btn-link " href="${pageContext.request.contextPath }/product/category?indexPage=1&txtSearch=${nameCategory}">${nameCategory}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div><!-- End sidebar categories-->

                        <h3 class="sidebar-title">Tags</h3>
                        <div class="sidebar-item tags">
                            <ul>
                                <li><a href="${pageContext.request.contextPath }/product/search?indexPage=1&txtSearch=${"Dress"}">Dress</a></li>
                                <li><a href="${pageContext.request.contextPath }/product/search?indexPage=1&txtSearch=${"Shirt"}">Shirt</a></li>
                                <li><a href="${pageContext.request.contextPath }/product/search?indexPage=1&txtSearch=${"Jean"}">Jean</a></li>
                            </ul>
                        </div><!-- End sidebar tags-->

                    </div><!-- End sidebar -->

                </div><!-- End blog sidebar -->

                <div class="col-lg-8 entries">
                    <div class="pos_page">
                        <div class="container">
                            <!--pos page inner-->
                            <div class="pos_page_inner">
                                <div class="row">
                                    <div class="col-12">
                                        <!--shop toolbar start-->
                                        <div class="shop_toolbar mb-35"
                                             style="padding-bottom: 10px;margin-bottom: 10px !important;">


                                            <div class="list_button">
                                                <ul class="nav" role="tablist">
                                                    <li>
                                                        <a class="active" data-toggle="tab" href="#large" role="tab"
                                                           aria-controls="large" aria-selected="true"><i
                                                                class="fa fa-th-large"></i></a>
                                                    </li>
                                                    <li>
                                                        <a data-toggle="tab" href="#list" role="tab"
                                                           aria-controls="list" aria-selected="false"><i
                                                                class="fa fa-th-list"></i></a>
                                                    </li>
                                                </ul>
                                            </div>


                                            <div class="page_amount">
                                                <p>Showing ${requestScope.start}–${requestScope.en} of ${requestScope.count} results</p>
                                            </div>
<%--                                            <div class="select_option">--%>
<%--                                                <form action="#">--%>
<%--                                                    <label>Sort By</label>--%>
<%--                                                    <label for="short"></label>--%>
<%--                                                    <select name="order_by" id="short">--%>
<%--                                                        <option selected="" value="1">Position</option>--%>
<%--                                                        <option value="Lowest">Price: Lowest</option>--%>
<%--                                                        <option value="Highest">Price: Highest</option>--%>
<%--                                                    </select>--%>
<%--                                                </form>--%>
<%--                                            </div>--%>
                                        </div>
                                        <!--shop toolbar end-->
                                    </div>
                                </div>

                                <!--shop tab product-->
                                <div class="shop_tab_product shop_fullwidth">
                                    <div class="tab-content" id="myTabContent">
                                        <div class="tab-pane fade show active" id="large" role="tabpanel">
                                            <div class="row">

                                                <c:if test="${requestScope.productList == null}">
                                                    <h5 class="text-center">No product in shop!</h5>
                                                </c:if>

                                                <c:forEach items="${requestScope.productList}" var="p">
                                                    <c:url value="/assets/user/img/product/${p.getImage()}"
                                                           var="imgUrl"/>
                                                    <c:choose>
                                                        <c:when test="${p.getStatus() == 0}">
                                                            <c:url value="/assets/user/img/cart/span-new2.png"
                                                                   var="statusUrl"/>
                                                        </c:when>
                                                        <c:when test="${p.getStatus() == 1}">
                                                            <c:url value="/assets/user/img/cart/span-hot1.png"
                                                                   var="statusUrl"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:url value="/assets/user/img/cart/span-new2.png"
                                                                   var="statusUrl"/>
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <c:set value="${p.getName()}" var="name"/>
                                                    <c:set value="${p.getPrice()}$" var="price"/>

                                                    <div class="col-lg-3 col-md-4 col-sm-6"
                                                         style="margin-left: 10px; width: 207px;">
                                                        <div class="single_product">
                                                            <div class="product_thumb">
                                                                <a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}"><img
                                                                        src="${imgUrl}" alt="Image_Product"></a>
                                                                <div class="img_icone">
                                                                    <img src="${statusUrl}" alt="status_icon">
                                                                </div>
                                                            </div>

                                                            <div class="product_content">
                                                                <span class="product_price">${price}</span>
                                                                <h3 class="product_title"><a
                                                                        href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}">${name}</a>
                                                                </h3>
                                                            </div>
                                                            <div class="product_info">
                                                                <ul style="padding: 0">
                                                                    <li style="margin-bottom: 5px; width: 150px;">
                                                                        <a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}">View Detail</a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>


                                            </div>
                                        </div>

                                        <div class="tab-pane fade" id="list" role="tabpanel">

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

                                                <div class="product_list_item mb-35"
                                                     style="margin-left: 10px !important;">
                                                    <div class="row align-items-center">
                                                        <div class="col-lg-3 col-md-5 col-sm-6">
                                                            <div class="product_thumb">
                                                                <a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}"><img src="${imgUrl1}"
                                                                                 alt="Image_Product"></a>
                                                                <div class="img_icone">
                                                                    <img src="${statusUrl1}" alt="Icon_status">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-9 col-md-7 col-sm-6">
                                                            <div class="list_product_content">
                                                                <div class="product_ratting">
                                                                    <ul style="padding: 0 !important;">
                                                                        <li><a href="#"><i class="fa fa-star"></i></a>
                                                                        </li>
                                                                        <li><a href="#"><i class="fa fa-star"></i></a>
                                                                        </li>
                                                                        <li><a href="#"><i class="fa fa-star"></i></a>
                                                                        </li>
                                                                        <li><a href="#"><i class="fa fa-star"></i></a>
                                                                        </li>
                                                                        <li><a href="#"><i class="fa fa-star"></i></a>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                                <div class="list_title">
                                                                    <h3><a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}">${name1}</a></h3>
                                                                </div>
                                                                <p class="design">
                                                                        ${description1}
                                                                </p>

                                                                <div class="content_price">
                                                                    <span>$${price1}</span>
                                                                </div>
                                                                <div class="add_links">
                                                                    <ul style="padding: 0 !important;">
                                                                        <li><a href="#" title="add to cart"><i
                                                                                class="fa fa-shopping-cart"
                                                                                aria-hidden="true"></i></a></li>
                                                                        <li><a href="#" title="add to wishlist"><i
                                                                                class="fa fa-heart"
                                                                                aria-hidden="true"></i></a></li>

                                                                        <li><a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}"
<%--                                                                               data-toggle="modal"--%>
                                                                               data-target="#modal_box"
                                                                               title="Quick view"><i
                                                                                class="fa fa-eye"
                                                                                aria-hidden="true"></i></a></li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <!--shop tab product end-->

                                <c:choose>
                                    <c:when test="${requestScope.checkCategorySearch != null}">
                                        <div class="pagination_style shop_page text-center">
                                            <div class="page_number text-center" style="width: 100%;">
                                                <ul style="padding: 0 !important;margin: auto auto !important;" class="text-center">

                                                    <li><a style="color: black; margin: 0!important;padding: 0!important;" class="btn btn-link" href="${pageContext.request.contextPath }/product/category?indexPage=${requestScope.indexPage - 1}&txtSearch=${requestScope.txtSearch}">Previous</a></li>
                                                    <c:forEach begin="1" end="${requestScope.end}" var="i">
                                                        <c:set value="black" var="color"/>
                                                        <c:if test="${requestScope.indexPage == i}">
                                                            <c:set value="blue" var="color"/>
                                                        </c:if>
                                                        <li><a class="btn btn-link" style="color: ${color};margin: 0!important;padding: 0!important;" href="${pageContext.request.contextPath }/product/category?indexPage=${i}&txtSearch=${requestScope.txtSearch}">${i}</a></li>
                                                    </c:forEach>
                                                    <li><a style="color: black; margin: 0!important;padding: 0!important;" class="btn btn-link" href="${pageContext.request.contextPath }/product/category?indexPage=${requestScope.indexPage + 1}&txtSearch=${requestScope.txtSearch}">Next</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="pagination_style shop_page text-center">
                                            <div class="page_number text-center" style="width: 100%;">
                                                <ul style="padding: 0 !important;margin: auto auto !important;" class="text-center">
                                                    <li><a style="color: black; margin: 0!important;padding: 0!important;" class="btn btn-link" href="${pageContext.request.contextPath }/product/search?indexPage=${requestScope.indexPage - 1}&txtSearch=${requestScope.txtSearch}">Previous</a></li>
                                                    <c:forEach begin="1" end="${requestScope.end}" var="i">
                                                        <c:set value="black" var="color"/>
                                                        <c:if test="${requestScope.indexPage == i}">
                                                            <c:set value="blue" var="color"/>
                                                        </c:if>
                                                        <li><a class="btn btn-link" style="color: ${color};margin: 0!important;padding: 0!important;" href="${pageContext.request.contextPath }/product/search?indexPage=${i}&txtSearch=${requestScope.txtSearch}">${i}</a></li>
                                                    </c:forEach>
                                                    <li><a style="color: black; margin: 0!important;padding: 0!important;" class="btn btn-link" href="${pageContext.request.contextPath }/product/search?indexPage=${requestScope.indexPage + 1}&txtSearch=${requestScope.txtSearch}">Next</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>


                                <!--pagination style end-->
                            </div>
                            <!--pos home section end-->
                        </div>
                        <!--pos page inner end-->
                    </div>
                </div>
            </div><!-- End blog entries list -->
        </div>
    </section><!-- End Blog Section -->
</main><!-- End #main -->

<%@include file="/views/layouts/user/footer.jsp" %>

<!-- all js here -->
<script src="${pageContext.request.contextPath}/assets/user/js/jquery-1.12.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/user/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/assets/user/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/user/js/ajax-mail.js"></script>
<script src="${pageContext.request.contextPath}/assets/user/js/plugins.js"></script>
<script src="${pageContext.request.contextPath}/assets/user/js/main2.js"></script>

<!-- Vendor JS Files -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/assets/user/js/main.js"></script>
<script src="${pageContext.request.contextPath}/assets/user/js/main2.js"></script>

</body>

</html>