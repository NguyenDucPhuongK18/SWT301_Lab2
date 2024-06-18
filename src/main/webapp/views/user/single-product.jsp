<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Contact</title>
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
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">


    <link href="<c:url value="/assets/vendor/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/css/style1.css"/>" rel="stylesheet">
</head>
<body>

<!--pos page start-->
<div class="pos_page" style="margin-top: 7%;">
    <div class="container">
        <!--pos page inner-->
        <div class="pos_page_inner">
            <!--breadcrumbs area start-->
            <div class="breadcrumbs_area">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/home">home</a></li>
                                <li><i class="fa fa-angle-right"></i></li>
                                <li>single product</li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->

            <c:url value="/assets/user/img/product/${requestScope.product.getImage()}"
                   var="imgUrl"/>

            <c:choose>
                <c:when test="${requestScope.product.getStatus() == 0}">
                    <c:url value="/assets/user/img/cart/span-new2.png"
                           var="statusUrl"/>
                </c:when>
                <c:when test="${requestScope.product.getStatus() == 1}">
                    <c:url value="/assets/user/img/cart/span-hot1.png"
                           var="statusUrl"/>
                </c:when>
                <c:otherwise>
                    <c:url value="/assets/user/img/cart/span-new2.png"
                           var="statusUrl"/>
                </c:otherwise>
            </c:choose>

            <%@include file="/views/layouts/user/header.jsp" %>

            <c:set value="${requestScope.product.getName()}" var="name"/>
            <c:set value="${requestScope.product.getPrice()}" var="price"/>
            <c:set value="${requestScope.product.getCategory().getName()}" var="type"/>
            <c:set value="${requestScope.product.getDescription()}" var="description1"/>

            <!--product wrapper start-->
            <div class="product_details sidebar">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="row">
                            <div class="col-lg-6 col-md-6">
                                <div class="product_tab sidebar fix">

                                    <div class="tab-content produc_tab_c">

                                        <div class="tab-pane fade show active" id="p_tab1" role="tabpanel">
                                            <div class="modal_img">
                                                <a href="#"><img src="${imgUrl}" alt="" style="width: 100% !important;"></a>
                                                <div class="img_icone">
                                                    <img src="${statusUrl}" alt="">
                                                </div>
                                                <div class="view_img">
                                                    <a class="large_view" href="${imgUrl}"><i
                                                            class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="p_tab2" role="tabpanel">
                                            <div class="modal_img">
                                                <a href="#"><img src="${pageContext.request.contextPath}/assets/user/img/product/product3.jpg" style="width: 100% !important;" alt=""></a>
                                                <div class="img_icone">
                                                    <img src="${pageContext.request.contextPath}/assets/user/img/cart/cart17.jpg" alt="">
                                                </div>
                                                <div class="view_img">
                                                    <a class="large_view" href="${pageContext.request.contextPath}/assets/user/img/product/product4.jpg"><i
                                                            class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="p_tab3" role="tabpanel">
                                            <div class="modal_img">
                                                <a href="#"><img src="${imgUrl}" style="width: 100% !important;" alt=""></a>
                                                <div class="img_icone">
                                                    <img src="${pageContext.request.contextPath}/assets/user/img/cart/cart18.jpg" alt="">
                                                </div>
                                                <div class="view_img">
                                                    <a class="large_view" href="${pageContext.request.contextPath}/assets/user/img/product/product6.jpg"> <i
                                                            class="fa fa-search-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="product_tab_button text-center" style="margin-left: 20px;">
                                        <ul class="nav text-center" role="tablist">
                                            <li >
                                                <a class="active" data-toggle="tab" href="#p_tab1" role="tab"s
                                                   aria-controls="p_tab1" aria-selected="false">
                                                    <img style="width: 100% !important;" src="${pageContext.request.contextPath}/assets/user/img/cart/cart17.jpg" alt="">
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#p_tab2" role="tab" aria-controls="p_tab2"
                                                   aria-selected="false">
                                                    <img src="${pageContext.request.contextPath}/assets/user/img/cart/cart18.jpg" style="width: 100% !important;"
                                                                              alt="">
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#p_tab3" role="tab" aria-controls="p_tab3"
                                                   aria-selected="false"><img src="${pageContext.request.contextPath}/assets/user/img/cart/cart19.jpg" style="width: 100% !important;"
                                                                              alt=""></a>
                                            </li>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                <div class="product_d_right">
                                    <h1>${name}</h1>
                                    <div class="product_ratting mb-10">
                                        <ul style="padding: 0 !important;">
                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            <li><a href="#"> Write a review </a></li>
                                        </ul>
                                    </div>
                                    <div class="product_desc">
                                        <p>${description1}</p>
                                    </div>

                                    <div class="content_price mb-15">
                                        <span>$${price}</span>
                                    </div>

                                    <div class="content_price mb-15">
                                        <p style="font-weight: bolder">Type: ${type}</p>
                                    </div>

                                    <c:if test="${sessionScope.account == null}">
                                        <c:redirect url="/login"/>
                                    </c:if>

                                    <div class="box_quantity mb-20">
                                        <form action="${pageContext.request.contextPath}/member/cart/add" method="GET">
                                            <label for="id"></label>
                                            <input type="text" name="pid" id="id" hidden="" value="${requestScope.product.getId()}">
                                            <label for="quantity">Quantity</label>
                                            <input id="quantity" name="quantity" min="0" max="100" value="1" type="number">



                                            <button type="submit"><i class="fa fa-shopping-cart"></i> Add to cart</button>
                                        </form>
                                        <a href="#" title="Add to wishlist"><i class="fa fa-heart"
                                                                               aria-hidden="true"></i></a>
                                    </div>

<%--                                    <div class="product_d_size mb-20">--%>
<%--                                        <label for="group_1">size</label>--%>
<%--                                        <select name="size" id="group_1">--%>
<%--                                            <option value="1">S</option>--%>
<%--                                            <option value="2">M</option>--%>
<%--                                            <option value="3">L</option>--%>
<%--                                        </select>--%>
<%--                                    </div>--%>

<%--                                    <div class="sidebar_widget color">--%>
<%--                                        <h2>Choose Color</h2>--%>
<%--                                        <div class="widget_color">--%>
<%--                                            <ul>--%>
<%--                                                <li><a href="#"></a></li>--%>
<%--                                                <li><a href="#"></a></li>--%>
<%--                                                <li><a href="#"></a></li>--%>
<%--                                                <li><a href="#"></a></li>--%>
<%--                                            </ul>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>

<%--                                    <div class="product_stock mb-20">--%>
<%--                                        <p>299 items</p>--%>
<%--                                        <span> In stock </span>--%>
<%--                                    </div>--%>
<%--                                    <div class="wishlist-share">--%>
<%--                                        <h4>Share on:</h4>--%>
<%--                                        <ul>--%>
<%--                                            <li><a href="#"><i class="fa fa-rss"></i></a></li>--%>
<%--                                            <li><a href="#"><i class="fa fa-vimeo"></i></a></li>--%>
<%--                                            <li><a href="#"><i class="fa fa-tumblr"></i></a></li>--%>
<%--                                            <li><a href="#"><i class="fa fa-pinterest"></i></a></li>--%>
<%--                                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>--%>
<%--                                        </ul>--%>
<%--                                    </div>--%>

                                </div>
                            </div>
                            <!--product info start-->
                            <div class="product_d_info sidebar">
                                <div class="col-12">
                                    <div class="product_d_inner">
                                        <div class="product_info_button">
                                            <ul class="nav" role="tablist">
                                                <li>
                                                    <a class="active" data-toggle="tab" href="#info" role="tab"
                                                       aria-controls="info" aria-selected="false">More info</a>
                                                </li>
                                                <li>
                                                    <a data-toggle="tab" href="#sheet" role="tab" aria-controls="sheet"
                                                       aria-selected="false">Data sheet</a>
                                                </li>
                                                <li>
                                                    <a data-toggle="tab" href="#reviews" role="tab"
                                                       aria-controls="reviews" aria-selected="false">Reviews</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-content">
                                            <div class="tab-pane fade show active" id="info" role="tabpanel">
                                                <div class="product_info_content">
                                                    <p>Fashion has been creating well-designed collections since 2010.
                                                        The brand offers feminine designs delivering stylish separates
                                                        and statement dresses which have since evolved into a full
                                                        ready-to-wear collection in which every item is a vital part of
                                                        a woman's wardrobe. The result? Cool, easy, chic looks with
                                                        youthful elegance and unmistakable signature style. All the
                                                        beautiful pieces are made in Italy and manufactured with the
                                                        greatest attention. Now Fashion extends to a range of
                                                        accessories including shoes, hats, belts and more!</p>
                                                </div>
                                            </div>

                                            <div class="tab-pane fade" id="sheet" role="tabpanel">
                                                <div class="product_d_table">
                                                    <form action="#">
                                                        <table>
                                                            <tbody>
                                                            <tr>
                                                                <td class="first_child">Compositions</td>
                                                                <td>Polyester</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="first_child">Styles</td>
                                                                <td>Girly</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="first_child">Properties</td>
                                                                <td>Short Dress</td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </form>
                                                </div>
                                                <div class="product_info_content">
                                                    <p>Fashion has been creating well-designed collections since 2010.
                                                        The brand offers feminine designs delivering stylish separates
                                                        and statement dresses which have since evolved into a full
                                                        ready-to-wear collection in which every item is a vital part of
                                                        a woman's wardrobe. The result? Cool, easy, chic looks with
                                                        youthful elegance and unmistakable signature style. All the
                                                        beautiful pieces are made in Italy and manufactured with the
                                                        greatest attention. Now Fashion extends to a range of
                                                        accessories including shoes, hats, belts and more!</p>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="reviews" role="tabpanel">
                                                <div class="product_info_content">
                                                    <p>Fashion has been creating well-designed collections since 2010.
                                                        The brand offers feminine designs delivering stylish separates
                                                        and statement dresses which have since evolved into a full
                                                        ready-to-wear collection in which every item is a vital part of
                                                        a woman's wardrobe. The result? Cool, easy, chic looks with
                                                        youthful elegance and unmistakable signature style. All the
                                                        beautiful pieces are made in Italy and manufactured with the
                                                        greatest attention. Now Fashion extends to a range of
                                                        accessories including shoes, hats, belts and more!</p>
                                                </div>
                                                <div class="product_info_inner">
                                                    <div class="product_ratting mb-10">
                                                        <ul>
                                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                            <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                        </ul>
                                                        <strong>Posthemes</strong>
                                                        <p>09/07/2018</p>
                                                    </div>
                                                    <div class="product_demo">
                                                        <strong>demo</strong>
                                                        <p>That's OK!</p>
                                                    </div>
                                                </div>
                                                <div class="product_review_form">
                                                    <form action="#">
                                                        <h2>Add a review </h2>
                                                        <p>Your email address will not be published. Required fields are
                                                            marked </p>
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <label for="review_comment">Your review </label>
                                                                <textarea name="comment" id="review_comment"></textarea>
                                                            </div>
                                                            <div class="col-lg-6 col-md-6">
                                                                <label for="author">Name</label>
                                                                <input id="author" type="text">

                                                            </div>
                                                            <div class="col-lg-6 col-md-6">
                                                                <label for="email">Email </label>
                                                                <input id="email" type="text">
                                                            </div>
                                                        </div>
                                                        <button type="submit">Submit</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--product info end-->




                        <!--Related Products area start-->
                        <div class="new_product_area">
                            <div class="block_title">
                                <h3>Related Products</h3>
                            </div>
                            <div class="row">
                                <div class="product_active owl-carousel">

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

                                        <div class="col-lg-3">
                                            <div class="single_product">
                                                <div class="product_thumb">
                                                    <a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}"><img style="width: 100% !important;" src="${imgUrl1}"
                                                                                       alt=""></a>
                                                    <div class="img_icone">
                                                        <img  src="${statusUrl1}" alt="">
                                                    </div>
                                                    <div class="product_action">
                                                        <a href="#"> <i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                                <div class="product_content">
                                                    <span class="product_price">$50.00</span>
                                                    <h3 class="product_title"><a href="${pageContext.request.contextPath }/product/detail?id=${p.getId()}">Curabitur
                                                        sodales</a></h3>
                                                </div>
                                                <div class="product_info">
                                                    <ul>
                                                        <li><a href="#" title=" Add to Wishlist ">Add to Wishlist</a></li>
                                                        <li><a href="#" data-toggle="modal" data-target="#modal_box"
                                                               title="Quick view">View Detail</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>


                                </div>
                            </div>
                        </div>
                        <!--Related Products area end-->
                    </div>

                </div>

            </div>
            <!--product details end-->

        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

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

</body>
</html>
