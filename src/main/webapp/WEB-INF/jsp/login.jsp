<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>

<link href="<c:url value="../ressources/styles/c62da57a.main.css" />"
	rel="stylesheet">
	
<link href="<c:url value="../ressources/styles/bootstrap.css" />"
	rel="stylesheet">

<title>Login Page</title>
<style>

</style>
</head>
<body onload='document.loginForm.username.focus();' class="container">


	<div id="login-box">

		<blockquote>Sign In</blockquote>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>




		<form class="form-horizontal"
			action="<c:url value='j_spring_security_check' />" method='POST'>
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="username"
						name="username" placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Password">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Sign in</button>
				</div>
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

	</div>

</body>
</html>