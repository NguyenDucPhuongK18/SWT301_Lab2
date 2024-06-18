<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Profile</title>
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

  <c:if test="${requestScope.msgAlert != null}">
    <c:out value="${requestScope.msgAlert}"/>
  </c:if>

  <main id="main" class="main" style="margin-top: 5%">
    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <c:set value="${requestScope.user.getFull_name()}" var="name"/>
              <c:set value="${requestScope.user.getGender()}" var="gender"/>
              <c:set value="${requestScope.user.getRole().getRole_name().toUpperCase()}" var="role"/>

              <c:url value="/assets/user/img/user/${requestScope.user.getAvatar()}" var="urlImg"/>

              <c:if test="${requestScope.user != null}">
                <img src="${urlImg}" alt="Profile" class="rounded-circle">
              </c:if>
              <c:if test="${requestScope.user == null}">
                <img src="${pageContext.request.contextPath}/assets/user/img/user/default.jpg" alt="Profile" class="rounded-circle">
              </c:if>
              <h2>${name}</h2>
              <h3>${role}</h3>
              <div class="social-links mt-2">
                <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered" role="tablist">

                <li class="nav-item" role="presentation">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" aria-selected="true" role="tab">Overview</button>
                </li>

                <li class="nav-item" role="presentation">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit" aria-selected="false" tabindex="-1" role="tab">Edit Profile</button>
                </li>

<%--                <li class="nav-item" role="presentation">--%>
<%--                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-settings" aria-selected="false" tabindex="-1" role="tab">Settings</button>--%>
<%--                </li>--%>

                <li class="nav-item" role="presentation">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password" aria-selected="false" tabindex="-1" role="tab">Change Password</button>
                </li>

              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview" role="tabpanel">
                  <h5 class="card-title">About</h5>
                  <p class="small fst-italic">If you can't find a reason to persevere. So give yourself an excuse to start again! Thank you!!! I LOVE LAN SO MUCH!!!</p>

                  <h5 class="card-title">Profile Details</h5>
                  <c:set value="${requestScope.user.getFull_name()}" var="name"/>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Full Name</div>
                    <div class="col-lg-9 col-md-8">${name}</div>
                  </div>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">User Account</div>
                    <div class="col-lg-9 col-md-8">${requestScope.user.getUsername()}</div>
                  </div>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Password</div>
                    <div class="col-lg-9 col-md-8 ">
                      <label>
                        <input style="border: none;" type="password" value="${requestScope.user.getPassword()}" readonly>
                      </label>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Gender</div>
                    <div class="col-lg-9 col-md-8">${gender}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Company</div>
                    <div class="col-lg-9 col-md-8">FPT Software</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Job</div>
                    <div class="col-lg-9 col-md-8">Web Designer</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Country</div>
                    <div class="col-lg-9 col-md-8">Viet Nam</div>
                  </div>
                  <c:set value="${requestScope.user.getAddress()}" var="address"/>
                  <c:set value="${requestScope.user.getPhone()}" var="phone"/>
                  <c:set value="${requestScope.user.getEmail()}" var="gmail"/>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Address</div>
                    <div class="col-lg-9 col-md-8">${address}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Phone</div>
                    <div class="col-lg-9 col-md-8">${phone}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8">${gmail}</div>
                  </div>

                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit" role="tabpanel">
                  <c:set value="image" var="info"/>
                  <!-- Profile Edit Form -->
                  <form action="${pageContext.request.contextPath}/edit_profile?info=${info}" method="POST" enctype="multipart/form-data">
                    <div class="row mb-3">
                        <label for="formFile" class="col-md-4 col-lg-3 form-label">Profile Image</label>
                      <div class="col-md-8 col-lg-9">
                        <c:url value="/assets/user/img/user/${requestScope.user.getAvatar()}" var="urlImg1"/>
                        <img class="col-md-8 col-lg-9" src="${urlImg1}" style="padding-bottom: 5px;" id="img_display" alt="Profile">
                        <input class="form-control" name="fileImage" accept=".jpg, .png" type="file" id="formFile">
                      </div>
                    </div>
                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                  <c:set value="info" var="info"/>
                  <form style="margin-top: 10px" action="${pageContext.request.contextPath}/edit_profile?info=${info}" method="POST">
                    <div class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Full Name</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="fullName" type="text" class="form-control" id="fullName" value="${name}">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label class="col-md-4 col-lg-3 col-form-label">Gender</label>
                      <div class="col-md-8 col-lg-9">
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="gender" id="Gender1" value="Male" checked/>
                          <label class="form-check-label" for="Gender1">Male</label>
                        </div>
                          <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="Gender2" value="Female" />
                            <label class="form-check-label" for="Gender2">Female</label>
                          </div>
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="address" type="text" class="form-control" id="Address" value="${address}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="phone" type="text" class="form-control" id="Phone" value="${phone}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="email" type="email" class="form-control" id="Email" value="${gmail}">
                      </div>
                    </div>
                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                </div>

                <div class="tab-pane fade pt-3" id="profile-change-password" role="tabpanel">
                  <!-- Change Password Form -->
                  <form action="${pageContext.request.contextPath}/change-password" method="POST">

                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="password" type="password" class="form-control" id="currentPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="new_password" class="col-md-4 col-lg-3 col-form-label">New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="new_password" type="password" class="form-control" id="new_password">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="renew_password" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="renew_password" type="password" class="form-control" id="renew_password">
                      </div>
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Change Password</button>
                    </div>
                  </form><!-- End Change Password Form -->

                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main>

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <%@include file="/views/layouts/user/footer.jsp"%>

  <!-- Vendor JS Files -->
  <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/assets/user/js/main.js"></script>

</body>

</html>