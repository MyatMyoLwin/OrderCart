<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="body">
	<%@ include file="headerLogin.jsp"%>

	<div class="container">
		<div class="col-md-8 col-md-offset-2">

			<label class="message">${orderSuccess}</label> 
			<label class="error">${orderUnsuccess}</label>
			<div class="table-responsive">
				<table class="table table-striped">
					<caption>Cart</caption>
					<tr>
						<th ng-click="orderByMe('id')">ID</th>
						<th ng-click="orderByMe('name')">Name</th>
						<th ng-click="orderByMe('price')">Price</th>
					</tr>
					<c:forEach var="product" items="${cartToShow}">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
						</tr>
					</c:forEach>
				</table>
				<div align="right">
					<a href="product" class="btn btn-info"> OK </a>
				</div>
			</div>


		</div>
	</div>

</body>
</html>