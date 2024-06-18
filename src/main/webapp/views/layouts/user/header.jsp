<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/tablib.jsp"%>
<!-- ======= Header ======= -->
<header id="header" class="header fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center justify-content-between">

        <a style="padding: 0 !important; margin-right: 40px !important; width: 78px !important;" href="${pageContext.request.contextPath}/home" class="logo d-flex align-items-center">
            <img src="${pageContext.request.contextPath}/assets/user/img/other/logo.png" alt="">
            <span>EWS</span>
        </a>

        <nav id="navbar" class="navbar" style="padding-left: 20% !important;">
            <ul style="margin: 0 !important; padding: 0 !important;">
                <li><a class="nav-link scrollto " href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/shop?indexPage=1">Shop</a></li>
                <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/contact">Contact</a></li>
                <c:if test="${requestScope.username == null}">
                    <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/login">Login</a></li>
                </c:if>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <nav class="header-nav ms-auto"  style="padding-left: 150px" >
            <c:set value="${requestScope.user.getFull_name()}" var="name"/>
            <c:set value="${requestScope.user.getRole().getRole_name().toUpperCase()}" var="role"/>
            <c:url value="/assets/user/img/user/${requestScope.user.getAvatar()}" var="urlImg"/>

            <c:if test="${requestScope.user == null}">
                <c:set value="Your Name" var="name"/>
                <c:set value="Role" var="role"/>
                <c:set value="${pageContext.request.contextPath}/assets/user/img/user/default.jpg" var="urlImg"/>
            </c:if>

            <ul class="d-flex align-items-center">
                <li class="nav-item dropdown pe-3">
                    <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                        <img src="${urlImg}" alt="Profile" class="rounded-circle">
                        <span class="d-none d-md-block dropdown-toggle ps-2">${name}</span>
                    </a>


                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                        <li class="dropdown-header">
                            <h6>${name}</h6>
                            <span>${role}</span>
                        </li>
                        <li>
                            <hr style="margin: 0" class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath}/profile">
                                <i class="bi bi-person"></i>
                                <span>My Profile</span>
                            </a>
                        </li>
                        <li>
                            <hr style="margin: 0" class="dropdown-divider">
                        </li>

                        <c:if test="${requestScope.username != null}">
                            <li>
                                <a class="dropdown-item d-flex align-items-center"
                                   href="${pageContext.request.contextPath}/logout">
                                    <i class="bi bi-box-arrow-right"></i>
                                    <span>Sign Out</span>
                                </a>
                            </li>
                        </c:if>

                        <li>
                            <hr style="margin: 0" class="dropdown-divider">
                        </li>
                    </ul>
                </li>

                <li style="padding-left: 30px" class="nav-item">
                    <a class="nav-link nav-icon" href="${pageContext.request.contextPath}/member/cart">
                        <i style="font-size: 30px" class="bi bi-basket2"></i>

                        <span class="badge bg-primary badge-number">
                            <c:choose>
                                <c:when test="${requestScope.amount != null}">
                                    <c:out value="${requestScope.amount}"/>
                                </c:when>
                                <c:when test="true"><%=0%></c:when>
                            </c:choose>
                        </span>
                    </a>
                </li>
            </ul>
        </nav>

    </div>
</header>