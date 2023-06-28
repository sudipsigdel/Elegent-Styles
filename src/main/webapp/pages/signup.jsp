<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Elegent Styles | Register</title>
    <link rel="stylesheet" type= "text/css" href="${pageContext.request.contextPath}/style/style.css" />
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
  	String errorMessage =(String) request.getAttribute("dublicateEntry");
  		%>
  		<%if(errorMessage != null){
  			
  			
  			%>
  			
  			<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
      <p></p>
        <p><%=errorMessage %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
  			
  			
  			<% 	
  		}%>
  		
 
 
 
    <div class="Signup-main">
      <div class="Signup-left">
        <img src="${pageContext.request.contextPath}/images/signup.png"  />
      </div>

      <div class="Signup-right">
        <center>
          <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo.png"  />
          </a>
          <p class="common-text">Register</p>
        </center>

        <form action="${pageContext.request.contextPath}/registerServlet" method="post"  enctype="multipart/form-data">
          <div class="signup-form">
            <div class="row">
              <div class="column">
                <label for="name">Name</label>
                <br />
                <input
                  type="text"
                  name="name"
                  placeholder="Your Name"
                  required
                />
              </div>

              <div class="column">
                <label for="address">Address</label>
                <br />
                <input
                  type="text"
                  name="address"
                  placeholder="Your Address"
                  required
                />
              </div>
            </div>

            <div class="row">
              <div class="column">
                <label for="email">Email</label>
                <br />
                <input
                  type="email"
                  name="email"
                  placeholder="Your Email"
                  required
                />
              </div>

              <div class="column">
                <label for="phone">Phone Number</label>
                <br />
                <input
                  type="number"
                  name="phone"
                  placeholder="Your Phone Number"
                  required
                />
              </div>
            </div>

            <div class="row">
              <div class="column">
                <label for="password">Password</label>
                <br />
                <input
                  type="password"
                  name="password"
                  placeholder="Your Password"
                  required
                />
              </div>

              <div class="file">
                <label for="image">Upload Image</label>

                <p>
                  <input type="file" name="image" id="" accept=".png, .jpg, .jpeg"  required />
                </p>
              </div>
            </div>

            <div class="column">
              <button class="common-button" type="submit" name="login">
                Create account
              </button>
            </div>
          </div>
        </form>

        <center>
          <div class="column">
            <p class="Redirect">
              Already have an account?
              <a href="${pageContext.request.contextPath}/pages/login.jsp"
                ><span class="span-color">Login here</span></a
              >
            </p>
          </div>

          <a href="t&c.html" class="hover" target="_blank">
            <p class="term">Terms & Conditions</p>
          </a>
        </center>
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
