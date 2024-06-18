<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Order</title>
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

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">


    <link href="<c:url value="/assets/vendor/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/user/css/style1.css"/>" rel="stylesheet">
</head>
<body>
<%@include file="/views/layouts/user/header.jsp"%>
            <!--pos page start-->
            <div class="pos_page" style="margin-top: 6%">
                <div class="container">
                    <!--pos page inner-->
                    <div class="pos_page_inner">  
                         <!--breadcrumbs area start-->
                        <div class="breadcrumbs_area">
                            <div class="row">
                                <div class="col-12">
                                    <div class="breadcrumb_content">
                                        <ul>
                                            <li><a href="#">home</a></li>
                                            <li><i class="fa fa-angle-right"></i></li>
                                            <li>checkout</li>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--breadcrumbs area end-->




                        <!--Checkout page section-->
                        <div class="Checkout_section">

                            <div class="checkout_form">
                                    <div class="row">
                                        <form action="${pageContext.request.contextPath}/checkout" class="row" method="post">
                                            <div class="col-lg-6 col-md-6">
                                                <h3>Billing Details</h3>
                                                <div class="row">

                                                    <c:set value="${requestScope.user.getAddress()}" var="address"/>
                                                    <c:set value="${requestScope.user.getPhone()}" var="phone"/>

                                                    <div class="col-12 mb-30">
                                                        <label for="address"> Address <span>*</span></label>
                                                        <input id="address" name="address" value="${address}" type="text">
                                                    </div>
                                                    <div class="col-lg-12 mb-30">
                                                        <label for="phone">Phone<span>*</span></label>
                                                        <input id="phone" name="phone" type="text" value="${phone}">
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="order-notes">
                                                            <label for="order_note">Order Notes</label>
                                                            <textarea name="note" style="height: 200px !important;" id="order_note" placeholder="Notes about your order, e.g. special notes for delivery.">Notes about your order, e.g. special notes for delivery.</textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-lg-6 col-md-6">
                                                <h3>Your order</h3>
                                                <div class="order_table table-responsive mb-30">
                                                    <table class="table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th>Product</th>
                                                            <th>Total</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:set value="0" var="CART_TOTALS"/>
                                                        <c:set value="0" var="total_discount"/>
                                                        <c:set value="0" var="total_price"/>
                                                        <c:set value="0" var="total_quantity"/>
                                                        <c:set value="0" var="total_price_origin"/>

                                                        <tbody>
                                                        <c:forEach items="${sessionScope.cart}" var="map">
                                                            <c:set value="${map.value.getProduct().getPrice()}" var="price"/>
                                                            <c:set value="${map.value.getProduct().getDiscount()}" var="discount"/>
                                                            <c:set value="${map.value.getProduct().getName()}" var="name"/>
                                                            <c:set value="${map.value.getQuantity()}" var="quantity"/>

                                                            <c:set value="${price * quantity - (quantity * discount)}" var="product_total"/>
                                                            <c:set value="${CART_TOTALS + product_total}" var="CART_TOTALS"/>
                                                            <c:set value="${total_discount + (quantity * discount)}" var="total_discount"/>
                                                            <c:set value="${total_price + product_total}" var="total_price"/>
                                                            <c:set value="${total_quantity + 1}" var="total_quantity"/>
                                                            <c:set value="${total_price_origin + (quantity * price)}" var="total_price_origin"/>

                                                            <tr>
                                                                <td>${name} <strong> × ${quantity}</strong></td>
                                                                <td>$${product_total}</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>

                                                        <!-- Sau vòng lặp, bạn có thể sử dụng các biến đã tính toán ở trên -->
                                                        <label>
                                                            <input type="number" name="quantity" value="${total_quantity}" hidden=""/>
                                                        </label>
                                                        <label>
                                                            <input type="number" name="status" value="1" hidden=""/>
                                                        </label>
                                                        <label>
                                                            <input type="number" name="total_discount" value="${total_discount}" hidden=""/>
                                                        </label>
                                                        <label>
                                                            <input type="number" name="total_price" value="${total_price_origin - total_discount}" hidden=""/>
                                                        </label>
                                                        <label>
                                                            <input type="number" name="total_price_origin" value="${total_price_origin}" hidden=""/>
                                                        </label>
                                                        <label>
                                                            <input type="number" name="total_quantity" value="${total_quantity}" hidden=""/>
                                                        </label>

                                                        <tfoot>

                                                        <tr class="order_total">
                                                            <th>Order Total</th>
                                                            <td><strong>$${CART_TOTALS}</strong></td>
                                                        </tr>
                                                        </tfoot>
                                                    </table>
                                                </div>
                                                <div class="payment_method text-center">
                                                    <div class="order_button">
                                                        <button type="submit">Order Now!</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>        
                        </div>
                        <!--Checkout page section end-->
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

