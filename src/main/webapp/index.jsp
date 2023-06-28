<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="resources.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Elegent Styles</title>
<link rel="stylesheet" href="style/style.css" />
<link rel="apple-touch-icon" sizes="180x180"
	href="images/favicon/apple-touch-icon.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="images/favicon/favicon-32x32.png" />
<link rel="icon" type="image/png" sizes="16x16"
	href="images/favicon/favicon-16x16.png" />
<link rel="manifest" href="favicon/site.webmanifest" />
<script src="js/script.js"></script>
</head>

<body>
<%
	String itemAddedMessage =(String) request.getAttribute("itemAdded");
	%>
	<%if(itemAddedMessage != null){
		%>
		
		<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=itemAddedMessage %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
		
		<% 	
	}%> 
	
	
	<%
	String itemAddedError =(String) request.getAttribute("cartError");
	%>
	<%if(itemAddedError != null){%> 
	
		<div class="overlay" style="display: block"></div>
    	<div class="modal" style="display: block">
      	<div class="delete_txt">
        <p><%=itemAddedError %></p>
        <button class="dismiss" onClick="hideModal()">Okay</button>
      </div>
    </div>
	
	<% 	
	}
	%> 
	<div class="margin">
		<!-- Header section containing logo in left and icons in right. -->
		<div class="header">
			<!-- logo -->
			<div class="header-left">
				<a href="index.jsp"> <img src="images/logo.png" alt="" />
				</a>
			</div>

			<!-- icons -->
			<div class="header-right">
				<ul>
					<li>
						<form id="searchthis" action="index.jsp" style="display: inline"
							method="post">


							<input id="search-box" name="search" size="40" type="text"
								placeholder="Search" /> <input id="search-btn"
								value="Go" type="submit" />
						</form>
					</li>
					<li><a href="pages/cart.jsp"><img
							src="images/icons/cart.png" alt="" srcset="" class="icon" /></a>
					</li>

					<li><a href="pages/profile.jsp"><img
							src="images/icons/profile.png" alt="" srcset="" class="icon" /></a>
					</li>
				</ul>
			</div>
		</div>

		<!-- cover image section -->
		<div class="cover">
			<img src="images/cover.png" alt="" srcset="" />
		</div>

		<!-- main section -->
		
		<div class="main">
			<!-- Left side containing categories and best seller section -->
			<div class="main-left">
				<!-- home button -->
				<div class="home">
					<p>
						<form action="index.jsp" method="post">
							<input type="hidden" name="method" value = "all">
							<button name="reset">Home</button>
						</form>
					</p>
				</div>

				<!-- categories -->
				<div class="categories">
					<h2>Categories</h2>
					<ul>
						<li>

							<form action="index.jsp" method="post">
								<input type="hidden" name="method" value="Women's Wear">
								<input type="submit" value="Women's Wear">
							</form>

						</li>
						<li>
							<form action="index.jsp" method="post">
								<input type="hidden" name="method" value="Men's Wear">
								<input type="submit" value="Men's Wear">
							</form>
						
						</li>
						<li>
						
						<form action="index.jsp" method="post">
								<input type="hidden" name="method" value="Shoes">
								<input type="submit" value="Shoes">
							</form>
						
						</li>
						<li>
						
						<form action="index.jsp" method="post">
								<input type="hidden" name="method" value="Bags">
								<input type="submit" value="Bags">
							</form>
						
						</li>
					</ul>
				</div>

				<!-- best seller -->
				<div class="home">
					<p>
						<form action="index.jsp" method="post">
							<input type="hidden" name="method" value = "rating">
							<button name="reset">Best Selling</button>
						</form>
					</p>
				</div>
			</div>

			<!-- Right side containing products -->
			<div class="main-right">
				<sql:setDataSource var="dbConnection" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/coursework" user="root"
					password="" />


				<sql:query var="products" dataSource="${dbConnection}">

					<%
					String requestType = request.getParameter("method");
					String searchName = request.getParameter("search");
					
					
					
					if (searchName != null) {
					%>
              			SELECT * from `product` WHERE `product_name` LIKE  "%<%=searchName%>%"; 
              		<%
					} else if (requestType == null) {
					%>
              			
              		
              			SELECT * from `product`;
              		
              		<%
					} else if (requestType.equals("Women's Wear")) {
					%>
              			SELECT * from `product` WHERE `category` LIKE "Women%"; 
              		<%
					}else if (requestType.equals("Men's Wear")) {
					%>
              			SELECT * from `product` WHERE `category` LIKE "Men%"; 
              		<%
					}else if (requestType.equals("Shoes")) {
					%>
              			SELECT * from `product` WHERE `category` LIKE "Shoe%"; 
              		<%
					}else if (requestType.equals("Bags")) {
					%>
              			SELECT * from `product` WHERE `category` LIKE "Bag%"; 
              		<%
					}
					else if (requestType.equals("all")) {
						%>
						SELECT * from `product`;
	              		<%
						}else if (requestType.equals("rating")) {
							%>
							SELECT * from `product` ORDER BY rating DESC;
		              		<%
							}
					%>
				</sql:query>
				<!-- products -->
				<div class="outlet">

					<c:forEach var="product" items="${products.rows}">
						<!-- Create a product card here -->
						<div class="items">
							<img class="cloth-image"
								src="images/product/${product.product_image} "
								alt="image of cloth" />
							<div class="type">
								<p class="item-type">
									<c:out value="${product.product_name} " />
								</p>
								<p class="item-descriptive">
									<c:out value="${product.category} " />
								</p>
								<p class="item-descriptive">
									<c:out value="${product.product_type} " />
								</p>
								
								
								<c:if test="${product.rating  == 1}">
									<img src="images/icons/1star.png" />
								</c:if>
								
								<c:if test="${product.rating  == 2}">
									<img src="images/icons/starTwo.png" />
								</c:if>
								
								<c:if test="${product.rating  == 3}">
									<img src="images/icons/3star.png" />
								</c:if>
								
								<c:if test="${product.rating  == 4}">
									<img src="images/icons/4star.png" />
								</c:if>
								
								<c:if test="${product.rating  == 5}">
									<img src="images/icons/5star.png" />
								</c:if>
								
								
								
								<!-- <p class="rating">Rating</p> -->
								<p class="price">
									$
									<c:out value="${product.price} " />
								</p>

								<c:if test="${product.stock >0}">
									<div class="cart">
											<p class="addToCart">Add To Cart</p>
											<form action="cart" method="post">
												<input type="hidden" name="productId"
													value="${product.product_id}" >
												<button class="cartButton" type="submit" name="addToCart"
													onclick="">
													<img class="cartImg" src="images/icons/addToCart.png"
														alt="image" />
												</button>


											</form>
										</div>
								</c:if>
								<c:if test="${product.stock <=0}">
									<p style="color:red">Out Of Stock</p>
								</c:if>








							</div>
						</div>
					</c:forEach>


				</div>
			</div>
		</div>
	</div>

	<!--Footer section of website containing copyright information-->
	<footer class="final">
		<div class="footer">
			<div class="footer-left">
				<h3 style="margin: 2% 0 2% 0">About company</h3>
				<p>Elegant style is a boutique that have been serving to loyal
					customer for more than five years. The company have sold over more
					than 5000 products in a very decent price. The company constantly
					open many schemes to the customer.</p>
				<p class="tc">
					<a href="pages/t&c.html" target="_blank"> Terms & Conditions </a>
				</p>
			</div>

			<div class="footer-center">
				<div>
					<h3>Categories</h3>
					<ul>
						<li>Men's Wear</li>
						<li>Women's Wear</li>
						<li>Shoes</li>
						<li>Bags</li>
					</ul>
				</div>

				<div>
					<h3>Best Seller</h3>
					<ul>
						<li>Air Jordan</li>
						<li>Side Bag</li>
						<li>Crop Top</li>
						<li>Trousers</li>
					</ul>
				</div>
			</div>

			<div class="footer-right">
				<center style="padding-top: 5%">
					<img src="images/footer.png" alt="" srcset="" width="70%" />
				</center>
				<br />
				<div class="social-media">
					<ul>
						<li><a href="#"><img
								src="images/icons/facebook.png" alt="" srcset="" class="icon" /></a>
						</li>

						<li><a href="#"><img
								src="images/icons/insta.png" alt="" srcset="" class="icon" /></a>
						</li>

						<li><a href="#"><img
								src="images/icons/tiktok.png" alt="" srcset="" class="icon" /></a>
						</li>
					</ul>
				</div>
			</div>
	</footer>
	<!-- Endnote -->
	<div class="copyright">
		<br> &copy2023 Elegent Styles. All Rights Reserved. <br />
	</div>
	</div>
	<script>
		
		function hideModal() {
			document.querySelector(".overlay").style.display = "none";
			document.querySelector(".modal").style.display = "none";
		}

		document.querySelector(".cancel").addEventListener("click", hideModal);
	</script>
</body>
</html>
