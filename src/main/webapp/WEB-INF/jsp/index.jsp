<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Upemgur App</title>



<link href="<c:url value="../ressources/styles/bootstrap.css" />"
	rel="stylesheet">
	
<link href="<c:url value="../ressources/styles/c62da57a.main.css" />"
rel="stylesheet">


</head>
<body>

	<!-- For login user -->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" id="X-CSRF-Token"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
		
		var isAdmin = ${isAdmin}
	</script>

	<c:if test="${connectedUser != null}">
		<div class="welcome">
			<blockquote>
				<p>
					User : ${connectedUser} | <a
						href="javascript:formSubmit()"> Logout</a>
				</p>
			</blockquote>
		</div>
	</c:if>





	<script src="<c:url value="../ressources/scripts/jquery.js" />"></script>
	<script src="<c:url value="../ressources/scripts/handlebars.js" />"></script>
	<script src="<c:url value="../ressources/scripts/bootstrap.js" />"></script>
	<script src="<c:url value="../ressources/scripts/ember.js" />"></script>
	<script src="<c:url value="../ressources/scripts/ember-data.js" />"></script>

	<script
		src="<c:url value="../ressources/scripts/privateApp/89fe50b0.components.js" />"></script>

	<script
		src="<c:url value="../ressources/scripts/privateApp/e3859bd0.templates.js" />"></script>
	<script
		src="<c:url value="../ressources/scripts/privateApp/8af06df6.main.js" />"></script>


</body>
</html>
