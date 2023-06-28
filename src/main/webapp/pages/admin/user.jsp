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
    <title>ADMIN | User</title>
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
	String roleAdd = (String) request.getAttribute("addRole");
	%>
	<%
	if (roleAdd != null) {
	%>
	
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=roleAdd %></p>
        <button class="dismiss" onClick="hideModal()">Okay</button>
      </div>
    </div>
	
	
	
	<%
	}
	%>
	
	<%
	String roleAddError = (String) request.getAttribute("addRoleError");
	%>
	<%
	if (roleAddError != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=roleAddError %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
	
	
	<%
	}
	%>
	
	<%
	String userNotFound = (String) request.getAttribute("userNotFound");
	%>
	<%
	if (userNotFound != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=userNotFound %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
	
	
	<%
	}
	%>
	
	<%
	String removeRole = (String) request.getAttribute("removeRole");
	%>
	<%
	if (removeRole != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p>Role Removed Successfully</p>
        <button class="dismiss" onClick="hideModal()">Okay</button>
      </div>
    </div>
	
	
	<%
	}
	%>
	
	<%
	String removeRoleError = (String) request.getAttribute("removeRoleError");
	%>
	<%
	if (removeRoleError != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=removeRoleError %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
	
	
	<%
	}
	%>
	
	<%
	String notFound = (String) request.getAttribute("userNotFoundRemove");
	%>
	<%
	if (notFound != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=notFound %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
	
	<%
	}
	%>
	
	<%
	String deleteUserSuccess = (String) request.getAttribute("deleteUser");
	%>
	<%
	if (deleteUserSuccess != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=deleteUserSuccess %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
	
	
	<%
	}
	%>
	
	<%
	String deleteUserSuccessError = (String) request.getAttribute("deleteUserError");
	%>
	<%
	if (deleteUserSuccessError != null) {
	%>
	
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=deleteUserSuccessError %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
	
	
	<%
	}
	%>
  
    <div class="first">
      <div class="nav">
        <div class="header-left">
          <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo-.png" alt="" />
          </a>
        </div>

        <div class="mid">
          <ul>
            <li><a href="${pageContext.request.contextPath}/pages/admin/product.jsp">Product</a></li>
            <li><a class="active" href="#">User</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/admin/order.jsp">Order</a></li>
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
              <li>
                <a href="${pageContext.request.contextPath}/pages/profile.jsp"
                  ><img
                    src="${pageContext.request.contextPath}/images/icons/logout.png"
                    alt="image"
                    srcset=""
                    class="icon"
                /></a>
                <p>Log Out</p>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="main">
        <div class="form-container">
          <div class="form form-add-role">
            <p class="common-text-ad">Add Role</p>
            <form action="${pageContext.request.contextPath}/roleEdit" method="post">
              <div class="addproduct-form">
                <div class="row">
                  <div class="column">
                    <label for="name">Enter email</label>
                    <input type="hidden" name="method" value="add"> 
                    <input
                      type="email"
                      name="add_role"
                      placeholder="Enter email of the user"
                      required
                    />
                    <button class="common-button" type="submit" name="login">
                      Make Admin
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>

          <div class="form form-remove-role">
            <p class="common-text-ad">Remove Role</p>
            <form action="${pageContext.request.contextPath}/roleEdit" method="post">
              <div class="addproduct-form">
                <div class="row">
                  <div class="column">
                    <label for="name">Enter Email</label>
                    <input type="hidden" name="method" value="remove"> 
                    <input
                      type="email"
                      name="remove_role"
                      placeholder="Enter email of the admin"
                      required
                    />
                    <button class="common-button" type="submit" name="login">
                      Remove Admin
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
        
        <sql:query var="users" dataSource="${dbConnection}">
				
				SELECT * FROM `register` WHERE isadmin = "true";
				
		</sql:query>
        
        <div class="second">
          <p class="common-text-ad">Admin Details</p>
          <table>
            <thead>
              <tr>
              
                <th>Name</th>
                <th>Address</th>
                <th>email</th>
                <th>phone</th>
                <th>image</th>
              </tr>
            </thead>
            <c:forEach var="userDetail" items="${users.rows}">
            <tbody>
              <tr>
                <td><c:out value="${userDetail.name} " /></td>
                <td><c:out value="${userDetail.address} " /></td>
                <td><c:out value="${userDetail.email} " /></td>
                <td><c:out value="${userDetail.phone} " /></td>
                <td>
                  <img
                    src="${pageContext.request.contextPath}/images/user/${userDetail.image}"
                    alt="Product 1"
                  />
                </td>
                
              </tr>
            </tbody>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
    <sql:query var="usersData" dataSource="${dbConnection}">
				
				SELECT * FROM `register`;
				
		</sql:query>

    <div class="second">
      <p class="common-text">User Details</p>
      <table>
        <thead>
          <tr>
            	<th>Name</th>
                <th>Address</th>
                <th>email</th>
                <th>phone</th>
                <th>image</th>
                <th>Action</th>
          </tr>
        </thead>
        <c:forEach var="userDatas" items="${usersData.rows}">
        <tbody>
          <tr>
            <td><c:out value="${userDatas.name} " /></td>
            <td><c:out value="${userDatas.address} " /></td>
            <td><c:out value="${userDatas.email} " /></td>
            <td><c:out value="${userDatas.phone} " /></td>
            <td><img src="${pageContext.request.contextPath}/images/user/${userDatas.image}" alt="Product 1" /></td>
            <td>
              <form action = "${pageContext.request.contextPath}/deleteUser" method = "post">
              	<input type="hidden" name="action" value="${userDatas.id}">
              	<button class="deleteButton" type="submit">Delete</button>              	
              </form>
            </td>
          </tr>
          
        </tbody>
        </c:forEach>
      </table>
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