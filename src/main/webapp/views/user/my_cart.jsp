<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Cart</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="<c:url value="/assets/user/img/other/favicon-16x16.png"/>" rel="icon" type="image/x-icon" />
    <link href="<c:url value="/assets/user/img/other/apple-touch-icon.png"/>" rel="apple-touch-icon" />
    <link rel="shortcut icon" href="<c:url value="/assets/user/img/other/favicon.ico"/>">

    <!-- all css here -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/plugin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/bundle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/style2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/css/responsive.css">
    <script src="${pageContext.request.contextPath}/assets/user/js/modernizr-2.8.3.min.js"></script>
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

<!--pos page start-->
<div class="pos_page" style="margin-top: 5%">
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
                                <li>Shopping Cart</li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->



            <!--shopping cart area start -->
            <div class="shopping_cart_area">
                <form action="${pageContext.request.contextPath}/member/cart/add" method="POST">
                    <div class="row" >
                        <div class="col-12" style="">
                            <div class="table_desc ">
                                <div class="cart_page table-responsive ">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="product_remove">Delete</th>
                                            <th class="product_thumb">Image</th>
                                            <th class="product_name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-price">Discount</th>
                                            <th class="product_quantity">Quantity</th>
                                            <th class="product_total">Total</th>
                                        </tr>
                                        </thead>
                                        <c:set value="${0}" var="CART_TOTALS"/>
                                        <tbody>
                                        <c:forEach items="${sessionScope.cart}" var="map">
                                            <c:url value="/assets/user/img/product/${map.value.getProduct().getImage()}"
                                                   var="imgUrl"/>
                                            <c:set value="${0}" var="product_total"/>
                                            <c:set value="${map.value.getProduct().getPrice()}" var="price"/>
                                            <c:set value="${map.value.getProduct().getDiscount()}" var="discount"/>
                                            <c:set value="${map.value.getProduct().getName()}" var="name"/>
                                            <c:set value="${map.value.getQuantity()}" var="quantity"/>
                                            <c:set value="${map.value.getProduct().getId()}" var="pId"/>
                                            <c:set value="${(quantity * price) - (quantity * discount) }" var="product_total"/>
                                            <c:set value="${CART_TOTALS + product_total}" var="CART_TOTALS"/>

                                            <tr>
                                                <td class="product_remove"><a href="${pageContext.request.contextPath}/member/cart/remove?pId=${pId}"><i class="fa fa-trash-o"></i></a></td>
                                                <td class="product_thumb"><a href="#"><img src="${imgUrl}" alt=""></a></td>
                                                <td class="product_name"><a href="#">${name}</a></td>
                                                <td class="product-price">$${price}</td>
                                                <td class="product-discount">$${discount}</td>

                                                <td class="product_quantity"><label>
                                                    <label for="id"></label>
                                                    <input type="text" name="pid" id="id" hidden="" value="${pId}">
                                                    <label>
                                                        <input name="quantity" min="0" max="1000" value="${quantity}" type="number">
                                                    </label>
                                                </label></td>

                                                <td class="product_total">$${product_total}</td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>

                                <div class="row">
                                    <div class="cart_submit col-12 text-center">
                                        <a href="${pageContext.request.contextPath}/shop?indexPage=1">
                                            <button type="button">Add More Product</button>
                                        </a>
                                        <button type="submit">Update cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--coupon code area start-->
                    <div class="coupon_area text-center">
                        <div class="row text-center">
<%--                            <div class="col-lg-6 col-md-6">--%>
<%--                                <div class="coupon_code">--%>
<%--                                    <h3>Coupon</h3>--%>
<%--                                    <div class="coupon_inner">--%>
<%--                                        <p>Enter your coupon code if you have one.</p>--%>
<%--                                        <input placeholder="Coupon code" type="text">--%>
<%--                                        <button type="submit">Apply coupon</button>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="col-lg-12 col-md-12 text-center">
                                <div class="coupon_code">
                                    <h3>Cart Totals</h3>
                                    <div class="coupon_inner">
<%--                                        <div class="cart_subtotal">--%>
<%--                                            <p>Subtotal</p>--%>
<%--                                            <p class="cart_amount">£215.00</p>--%>
<%--                                        </div>--%>
<%--                                        <div class="cart_subtotal ">--%>
<%--                                            <p>Shipping</p>--%>
<%--                                            <p class="cart_amount"><span>Flat Rate:</span> £255.00</p>--%>
<%--                                        </div>--%>
<%--                                        <a href="#">Calculate shipping</a>--%>

                                        <div class="cart_subtotal">
                                            <p>Total</p>
                                            <p class="cart_amount">$${CART_TOTALS}</p>
                                        </div>
                                        <div class="checkout_btn">
                                            <a href="${pageContext.request.contextPath}/checkout">Proceed to Checkout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--coupon code area end-->
                </form>
            </div>
            <!--shopping cart area end -->

        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->
<%@include file="/views/layouts/user/footer.jsp"%>

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
