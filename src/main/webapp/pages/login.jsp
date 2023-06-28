<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Elegent Styles | Login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/style.css" />
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
	String errorMessage = (String) request.getAttribute("invalidIdPassword");
	%>


	<%
	if (errorMessage != null) {
	%>
	<div class="overlay" style="display: block"></div>
    <div class="modal" style="display: block">
      <div class="delete_txt">
        <p><%=errorMessage %></p>
        <button class="dismiss" onClick="hideModal()">Dismiss</button>
      </div>
    </div>
    
    <script>
		
		function hideModal() {
			document.querySelector(".overlay").style.display = "none";
			document.querySelector(".modal").style.display = "none";
		}

		document.querySelector(".cancel").addEventListener("click", hideModal);
	</script>
	<%
	}
	%>
	
	




	<div class="login-main">
		<div class="login-left">
			<center>
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					src="${pageContext.request.contextPath}/images/logo.png" alt=""
					srcset="" />
				</a>
				<p class="common-text">Login</p>
			</center>

			<div class="login-form">
				<form action="${pageContext.request.contextPath}/loginServlet"
					method="post">
					<label for="phone">Username</label> <br /> <input type="email"
						name="email" placeholder="Your Email" required /> <br />
					<br /> <label for="password">Password</label> <br /> <input
						type="password" name="password" placeholder="Your Password"
						required /> <br />
					<br />
					<button class="common-button" type="submit" name="login">
						Login</button>
				</form>
			</div>

			<center>
				<p class="Redirect">
					Don't have an account? <a
						href="${pageContext.request.contextPath}/pages/signup.jsp"><span
						class="span-color">Create a free account</span></a>
				</p>
			</center>
		</div>

		<div class="login-right">
			<img src="${pageContext.request.contextPath}/images/login.png" alt=""
				srcset="" />
		</div>
	</div>

	

</body>
</html>
