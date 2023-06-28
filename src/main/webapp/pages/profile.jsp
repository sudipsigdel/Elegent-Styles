<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page import="model.userDetail"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Elegent Styles | Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/profile.css" />
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
    <link rel="manifest" href="${pageContext.request.contextPath}/images/favicon/site.webmanifest" />
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
				
				SELECT * FROM `register` WHERE email = "<%= userName %>";
				
			</sql:query>
	<c:forEach var="userDetail" items="${users.rows}">
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
              <a href="${pageContext.request.contextPath}/pages/cart.jsp"
                ><img
                  src="${pageContext.request.contextPath}/images/icons/cart.png"
                  alt=""
                  srcset=""
                  class="icon"
              /></a>
            </li>

            <li>
              <div class="info">
                <a href="${pageContext.request.contextPath}/pages/profile.jsp"
                  ><img
                    src="${pageContext.request.contextPath}/images/user/${userDetail.image}"
                    alt=""
                    srcset=""
                    class="icon"
                /></a>
                <p><c:out value="${userDetail.name} " /></p>
              </div>
            </li>

            <li>
              
              <div class="overlay" style="display: none"></div>
              <div class="modal" style="display: none">
                <div class="delete_txt">
                  <p>Do You Want to logout?</p>
                </div>
                <div class="delete_actions">
                  <form
                    action="${pageContext.request.contextPath}/logoutServlet"
                    method="post">
                    
                    <button class="delete">Yes</button>
                  </form>
                  <button class="cancel">No</button>
                </div>
              </div>	

              <button class="log" onclick="showModal()">
                <img
                  src="../images/icons/logoutB.png"
                  alt=""
                  srcset=""
                  class="icon"
                />
                <p>Log Out</p>
              </button>
              
              
            </li>
          </ul>
        </div>
      </div>
    </div>

    <p class="common-text">Profile</p>
    
    

    <div class="main">
      <div class="form">
        
          <div class="addproduct-form">
            <div class="left-right">
              <div class="row">
                <div class="column">
                  <div class="pic">
                    <img
                      src="${pageContext.request.contextPath}/images/user/${userDetail.image}"
                      alt=""
                      srcset=""
                    />
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="column">
                  <label>Name</label>
                  <p><c:out value="${userDetail.name} " /></p>
                </div>

                <div class="column">
                  <label>Email</label>
                  <p><c:out value="${userDetail.email} " /></p>
                </div>
              </div>

              <div class="row">
                <div class="column">
                  <label>Phone</label>
                  <p><c:out value="${userDetail.phone} " /></p>
                </div>

                <div class="column">
                  <label>Address</label>
                  <p><c:out value="${userDetail.address} " /></p>
                </div>
              </div>
            </div>

            <form action="${pageContext.request.contextPath}/pages/useredit.jsp" method="post">
            
            	<input type="hidden" name="id" value="${userDetail.id}">
            	<button class="edit-button" type="submit" name="login">
	              Edit Profile
	            </button>
            
            </form>
            
            
          </div>
          <a href="${pageContext.request.contextPath}/pages/admin/product.jsp">
              <button class="edit-button" style="margin-left:28%;" type="submit" name="login">
              
               	 Go to Admin Panel
               
              </button>
              </a>
      </div>
    </div>
    </c:forEach>

    <p class="common-text">Order History</p>
    
    <% 
     userDetail obj = new userDetail();
    int id = obj.userId(userName);
    boolean empty = true;
    %>
    
    <sql:query var="item" dataSource="${dbConnection}">
			SELECT  product.product_name, product.product_type, product.category, product.rating, product.price, product.product_image FROM purchase,product WHERE purchase.product_id = product.product_id AND  purchase.user_id = <%= id %>;
			
	</sql:query>

    <div class="table">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Category</th>
            <th>Rating</th>
            <th>Price</th>
            <th>Image</th>
          </tr>
        </thead>
		
        <tbody>
        <c:forEach var="productDetail" items="${item.rows}">
          <tr>
          
          <% empty = false; %>
            <td><c:out value="${productDetail.product_name} " /></td>
            <td><c:out value="${productDetail.product_type} " /></td>
            <td><c:out value="${productDetail.category} " /></td>
            <td><c:out value="${productDetail.rating }" /></td>
            <td>$<c:out value="${productDetail.price} " /></td>
            <td>
              <img src="${pageContext.request.contextPath}/images/product/${productDetail.product_image}" alt="Product 1" />
            </td>
          </tr>

         
          </c:forEach>
          <%if(empty){%>
           <tr >
           <td colspan=6 >
            	<p style="margin:1.5rem"><%= "Purchase History Is Empty" %></p>
            	</td>
           </tr>
           <% 	
            	  
            }
            %>
          
        </tbody>
      </table>
    </div>

    <!--Footer section of website containing copyright information-->
    <footer class="final">
      <div class="footer">
        <div class="footer-left">
          <h3 style="margin: 2% 0 2% 0">About company</h3>
          <p>
            Elegant style is a boutique that have been serving to loyal customer
            for more than five years. The company have sold over more than 5000
            products in a very decent price. The company constantly open many
            schemes to the customer.
          </p>
          <p class="tc">
            <a href="${pageContext.request.contextPath}/pages/t&c.html" target="_blank"> Terms & Conditions </a>
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
            <img src="${pageContext.request.contextPath}/images/footer.png" alt="" srcset="" width="70%" />
          </center>
          <br />
          <div class="social-media">
            <ul>
              <li>
                <a href="#"
                  ><img
                    src="${pageContext.request.contextPath}/images/icons/facebook.png"
                    alt=""
                    srcset=""
                    class="icon"
                /></a>
              </li>

              <li>
                <a href="#"
                  ><img
                    src="${pageContext.request.contextPath}/images/icons/insta.png"
                    alt=""
                    srcset=""
                    class="icon"
                /></a>
              </li>

              <li>
                <a href="#"
                  ><img
                    src="${pageContext.request.contextPath}/images/icons/tiktok.png"
                    alt=""
                    srcset=""
                    class="icon"
                /></a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Endnote -->
      <div class="copyright">
        <br />
        &copy2023 Elegent Styles. All Rights Reserved.
        <br />
      </div>
    </footer>
    <script>
    	function showModal() {
    	        document.querySelector(".overlay").style.display = "block";
    	        document.querySelector(".modal").style.display = "block";
    	      }
    	function hideModal() {
    	        document.querySelector(".overlay").style.display = "none";
    	        document.querySelector(".modal").style.display = "none";
    	      }

    	      document.querySelector(".cancel").addEventListener("click", hideModal);
    
    </script>
  </body>
</html>