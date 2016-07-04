<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register</title>

<spring:url value="/resources/css/errors.css" var="errorsCss" />
<link href="${errorsCss}" rel="stylesheet" />

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
			<h2>Register</h2>
			<label class="error">${message}</label>
			<form:form method="POST" action="/Order/register"
				commandName="customer" class="form-horizontal">
				<div class="form-group">
					<div class="col-sm-2">
						<form:label path="username"> username</form:label>
					</div>
					<div class="col-sm-5">
						<form:input path="username" class="form-control" type="text"
							placeholder="username" autofocus="autofocus"/>
					</div>
					<div class="col-sm-5">
						<form:errors path="username" class="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<form:label path="password">password</form:label>
					</div>
					<div class="col-sm-5">
						<form:input path="password" id="password" class="form-control"
							type="text" placeholder="password" />
					</div>
					<div class="col-sm-5">
						<form:errors path="password" class="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<form:label path="address">address</form:label>
					</div>
					<div class="col-sm-5">
						<form:input path="address" id="address" class="form-control"
							type="text" placeholder="address" />
					</div>
					<div class="col-sm-5">
						<form:errors path="address" class="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-info" value="Register" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>