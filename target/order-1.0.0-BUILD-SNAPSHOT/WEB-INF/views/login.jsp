<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<spring:url value="/resources/css/errors.css" var="errorsCss" />
<link href="${errorsCss}" rel="stylesheet" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<h2>Login</h2>
			<label class="error">${message}</label>
			<form method="POST"
				action="<c:url value='/j_spring_security_check' />"
				class="form-horizontal">
				<div class="form-group">
					<div class="col-sm-2">
						<label>User Name</label>
					</div>
					<div class="col-sm-5">
						<input class="form-control" type="text" name="j_username"
							placeholder="User Name" autofocus/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label>Password</label>
					</div>
					<div class="col-sm-5">
						<input id="password" class="form-control" name="j_password"
							type="password" placeholder="Password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
					</div>
					<div class="col-sm-2">
						<input type="submit" class="btn btn-info" value="Login" />
					</div>
					<div class="col-sm-5">
						<a href="register" class="btn btn-info" role="button">register</a>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			</form>
		</div>
	</div>
</body>
</html>
