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
    <title>ADMIN | Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/adminstyle.css" />
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
   <sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/coursework" user="root"
					password="" />
					
	<%
			HttpSession sessionName = request.getSession(false);
			
			String userName =(String) sessionName.getAttribute("user");
			
			%>
			<sql:query var="users" dataSource="${dbConnection}">
				
				SELECT name, image FROM `register` WHERE email = "<%= userName %>";
				
			</sql:query>
					
    <div >
      <div class="nav">
        <div class="header-left">
          <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo-.png" alt="" />
          </a>
        </div>

        <div class="mid">
          <ul>
            <li><a href="${pageContext.request.contextPath}/pages/admin/product.jsp">Product</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/admin/user.jsp">User</a></li>
            <li><a class="active" href="#">Order</a></li>
          </ul>
        </div>

        <div class="left">
        <c:forEach var="userDetail" items="${users.rows}">
          <div class="com-info">
            <img src="${pageContext.request.contextPath}/images/user/${userDetail.image}" alt="image" class="icon" style="height:4rem; width:4rem;">
            <div class="info">
              <p style="margin-top:5px;"><c:out value="${userDetail.name} " /></p>
              <p>Admin</p>
            </div>
          </div>
         </c:forEach>
          <div class="logout">
            <ul>
              <li>
                <a href="${pageContext.request.contextPath}/pages/profile.jsp"
                  ><img
                    src="${pageContext.request.contextPath}/images/icons/logout.png"
                    alt=""
                    srcset=""
                    class="icon"
                /></a>
                <p>Log Out</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
    <sql:query var="cart" dataSource="${dbConnection}">
				
				SELECT register.name,register.email, product.product_name, product.category, product.product_type, product.price, product.product_image FROM register, cart, product WHERE cart.user_id = register.id AND cart.product_id = product.product_id;
				
	</sql:query>

    <div class="second">
      <p class="common-text">Cart Details</p>
      <table>
        <thead>
          <tr>
            <th>Customer Name</th>
            <th>Customer Email</th>
            <th>Product Name</th>
            <th>Product Category</th>
            <th>Product Type</th>
            <th>Price</th>
            <th>Image</th>
          </tr>
        </thead>
        <c:forEach var="cartDetail" items="${cart.rows}">
        <tbody>
          <tr>
            <td><c:out value="${cartDetail.name} " /></td>
            <td><c:out value="${cartDetail.email} " /></td>
            <td><c:out value="${cartDetail.product_name} " /></td>
            <td><c:out value="${cartDetail.category} " /></td>
            <td><c:out value="${cartDetail.product_type} " /></td>
            <td>$<c:out value="${cartDetail.price} " /></td>
            <td><img src="${pageContext.request.contextPath}/images/product/${cartDetail.product_image}" alt="Product 1" /></td>
          </tr>
        </tbody>
        </c:forEach>
      </table>
    </div>
    
    
    <sql:query var="order" dataSource="${dbConnection}">
				
				SELECT register.name, register.email,product.product_name, product.category, product.product_type, product.price, product.product_image FROM register, purchase, product WHERE purchase.user_id = register.id AND purchase.product_id = product.product_id;
				
	</sql:query>

    <div class="third">
      <p class="common-text">Order History</p>
      <table>
        <thead>
          <tr>
            <th>Customer Name</th>
            <th>Customer Email</th>
            <th>Product Name</th>
            <th>Product Category</th>
            <th>Product Type</th>
            <th>Price</th>
            <th>Image</th>
          </tr>
        </thead>
        <c:forEach var="orderDetail" items="${order.rows}">
        <tbody>
          <tr>
            <td><c:out value="${orderDetail.name} " /></td>
            <td><c:out value="${orderDetail.email} " /></td>
            <td><c:out value="${orderDetail.product_name} " /></td>
            <td><c:out value="${orderDetail.category} " /></td>
            <td><c:out value="${orderDetail.product_type} " /></td>
            <td>$<c:out value="${orderDetail.price} " /></td>
            <td><img src="${pageContext.request.contextPath}/images/product/${orderDetail.product_image}" alt="Product 1" /></td>
          </tr>
        </tbody>
        </c:forEach>
      </table>
    </div>
  </body>
</html>