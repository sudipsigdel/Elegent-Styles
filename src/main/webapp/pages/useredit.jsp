<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Elegent Styles | Edit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/usercrud.css" />
    <link
      rel="apple-touch-icon"
      sizes="180x180"
      href="${pageContext.request.contextPath}/images/favicon/apple-touch-icon.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="32x32"
      href="${pageContext.request.contextPath}/images/favicon/favicon-32x32.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="16x16"
      href="${pageContext.request.contextPath}/images/favicon/favicon-16x16.png"
    />
    <link rel="manifest" href="favicon/site.webmanifest" />
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
  </head>

  <body>
  <%
	int id = Integer.parseInt(request.getParameter("id"));
	%>
	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/coursework" user="root" password="" />
		
		<sql:query var="user" dataSource="${dbConnection}">
				
				SELECT * FROM `register` WHERE id = <%=id%>;
				
	</sql:query>
	<c:forEach var="userDetail" items="${user.rows}">
    <div class="margin">
        <!-- Header section containing logo in left and icons in right. -->
        <div class="header">
          <!-- logo -->
          <div class="header-left">
            <a href="${pageContext.request.contextPath}/index.jsp">
              <img src="${pageContext.request.contextPath}/images/logo.png" alt="" />
            </a>
          </div>
  
          <!-- icons -->
          <div class="header-right">
            <ul>
              <li>
                <div class="info">
                  <a href="${pageContext.request.contextPath}/pages/profile.jsp"
                    ><img
                      src="${pageContext.request.contextPath}/images/user/${userDetail.image}"
                      alt=""
                      srcset=""
                      class="icon"
                  /></a>
                  <p><c:out value='${userDetail.name}'/></p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <p class="common-text">Edit Details</p>

      <div class="main">
        <div class="form">
          <form action="${pageContext.request.contextPath}/userEditServlet" method="post" enctype="multipart/form-data">
            <div class="addproduct-form">
              <div class="row">
              <input type="hidden" name="id" value="${userDetail.id}">
                <div class="column">
                  <label for="category">Email</label>
                  <input
                    type="text"
                    name="category"
                    id="email"
                    value="<c:out value='${userDetail.email}'/>"
                    disabled
                  />
                </div>

                <div class="column">
                  <label for="rating">Phone</label>
                  <input
                    type="number"
                    name="rating"
                    id="phone"
                    value="<c:out value='${userDetail.phone}'/>"
                    disabled
                  />
                </div>
              </div>

              <div class="row">
                <div class="column">
                  <label for="name">Name</label>
                  <input
                    type="text"
                    name="name"
                    value="<c:out value='${userDetail.name}'/>"
                    required
                  />
                </div>

                <div class="column">
                  <label for="stock">Password</label>
                  <input
                    type="password"
                    name="password"
                    required
                  />
                </div>
              </div>

              <div class="row">
                <div class="column">
                  <label for="type">Address</label>
                  <input
                    type="text"
                    name="address"
                    value="<c:out value='${userDetail.address}'/>"
                    required
                  />
                </div>

                <div class="column">
                    <div>
                      <label for="image">Upload Image</label>
  
                      <input
                        type="file"
                        name="image"
                        id=""
                        accept=".jpg, .png"
                        style="border: none"
                      />
                    </div>
                  </div>
              </div>

              <center>
                <button class="common-button" type="submit" name="login">
                  Update
                </button>
              </center>
            </div>
          </form>
        </div>
      </div>
    </div>
    </c:forEach>
  </body>
</html>