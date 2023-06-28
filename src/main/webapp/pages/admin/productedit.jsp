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
<title>ADMIN | Edit Product</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/adminstyle.css" />
<link rel="apple-touch-icon" sizes="180x180"
	href="${pageContext.request.contextPath}/images/favicon/apple-touch-icon.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="${pageContext.request.contextPath}/images/favicon/favicon-32x32.png" />
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.request.contextPath}/images/favicon/favicon-16x16.png" />
<link rel="manifest" href="favicon/site.webmanifest" />
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>

<body>
	<%
	int id = Integer.parseInt(request.getParameter("edit"));
	%>

	<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/coursework" user="root" password="" />
	<div class="first">
		<div class="nav">
			<div class="header-left">
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					src="${pageContext.request.contextPath}/images/logo-.png" alt="" />
				</a>
			</div>

			<div class="mid">
				<ul>
					<li><a class="active" href="${pageContext.request.contextPath}/pages/admin/product.jsp">Product</a></li>
					<li><a href="${pageContext.request.contextPath}/pages/admin/user.jsp">User</a></li>
				</ul>
			</div>
			<%
			HttpSession sessionName = request.getSession(false);
			
			String userName =(String) sessionName.getAttribute("user");
			
			%>
			<sql:query var="users" dataSource="${dbConnection}">
				
				SELECT name, image FROM `register` WHERE email = "<%= userName %>";
				
			</sql:query>

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
						<li><a
							href="${pageContext.request.contextPath}/pages/profile.jsp"><img
								src="${pageContext.request.contextPath}/images/icons/logout.png"
								alt="logout" srcset="" class="icon" /></a>
							<p>Log Out</p></li>
					</ul>
				</div>
			</div>
		</div>

		<p class="common-text">Edit Product</p>

		<sql:query var="product" dataSource="${dbConnection}">
				
				SELECT * FROM `product` WHERE product_id = <%=id%>;
				
	</sql:query>
		<div class="main">
			<div class="form">
				<c:forEach var="productDetail" items="${product.rows}">
					<form action="${pageContext.request.contextPath}/editProduct"
						method="post" enctype="multipart/form-data">


						<input type="hidden" name="product_id"
							value="<c:out value='${productDetail.product_Id}'/>">
						<div class="addproduct-form">
							<div class="row">
								<div class="column">
									<label for="name">Product Name</label> <input type="text"
										name="product_name" placeholder="Enter Product Name"
										value="<c:out value='${productDetail.product_name}'/>"
										required />
								</div>

								<div class="column">
									<label for="type">Product Type</label> <input type="text"
										name="product_type" placeholder="Enter Product Type"
										value="<c:out value='${productDetail.product_type}'/>"
										required />
								</div>
							</div>

							<div class="row">
								<div class="column">
									<label for="category">Category</label> <input type="text"
										name="category" placeholder="Enter Category"
										value="<c:out value='${productDetail.category}'/>" required />
								</div>

								<div class="column">
									<label for="rating">Rating</label> <input type="text"
										name="rating" value="<c:out value='${productDetail.rating}'/>"
										required />
								</div>
							</div>

							<div class="row">
								<div class="column">
									<label for="stock">Stock</label> <input type="text"
										name="stock" placeholder="Enter Stock"
										value="<c:out value='${productDetail.stock}'/>" required />
								</div>

								<div class="column">
									<label for="price">Price</label> <input type="text"
										name="price" placeholder="Enter Price (Rs)"
										value="<c:out value='${productDetail.price}'/>" required />
								</div>
							</div>

							<div class="row">
								<!-- can add a column here -->

								<div class="column">
									<div>
										<label for="image">Upload Image</label> <input type="file"
											name="product_image" id="" value="" accept=".jpg, .png"
											style="border: none" />
									</div>
								</div>
							</div>

							<center>
								<button class="common-button" type="submit">
									Update</button>
							</center>
						</div>

					</form>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>