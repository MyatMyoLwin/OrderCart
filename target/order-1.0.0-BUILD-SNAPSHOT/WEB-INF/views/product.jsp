<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<spring:url value="/resources/css/body.css" var="bodyCSS" />
<spring:url value="/resources/css/table.css" var="tableCSS" />
<spring:url value="/resources/css/input.css" var="inputCSS" />
<spring:url value="/resources/css/img.css" var="imgCSS" />
<spring:url value="/resources/images" var="images" />

<link href="${bodyCSS}" rel="stylesheet" type="text/css" />
<link href="${tableCSS}" rel="stylesheet" />
<link href="${inputCSS}" rel="stylesheet" />
<link href="${imgCSS}" rel="stylesheet" />

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<spring:url value="/resources/js/angular.js" var="angularJS" />
<script src="${angularJS}"></script>

<title>All Products</title>
</head>
<body class="body">
	<%@ include file="headerLogin.jsp"%>

	<div class="container">
		<div class="col-md-8 col-md-offset-2">

			<h2>Product List</h2>
			<br>

			<div ng-app="products" ng-controller="productsCtrl"
				ng-init='products = ${productList}' class="table-responsive">
				<table class="table table-striped">
					<tr class="buttonAndSearch">
						<td>Search</td>
						<td><input type="text" class="form-control" ng-model="search" /></td>
					</tr>
				</table>

				<div class="table-responsive">
					<table class="table table-striped">
						<caption>Products</caption>
						<tr>
							<th ng-click="orderByMe('id')">ID</th>
							<th ng-click="orderByMe('name')">Name</th>
							<th ng-click="orderByMe('price')">Price</th>
							<th>Actions</th>
						</tr>
						<tr
							ng-repeat="p in products | filter : search | orderBy : myOrderBy">
							<td>{{ p.id }}</td>
							<td>{{ p.name }}</td>
							<td>{{ p.price }}</td>
							<td><a href="addproduct?id={{ p.id }}" class="btn btn-info">
									Add to Cart</a></td>
						</tr>
					</table>
				</div>
			</div>
			
			<br>

			<ul class="pagination">
				<c:if test="${currentPage != 1}">
					<li><a href="product?page=${currentPage - 1}">Previous</a></li>
				</c:if>
				<c:forEach begin="1" end="${noOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<li class="active"><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="product?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages}">
					<li><a href="product?page=${currentPage + 1}">Next</a></li>
				</c:if>
			</ul>


			<c:if test="${fn:length(cart) gt 0}">
				<div class="table-responsive">
					<table class="table table-striped">
						<caption>Cart</caption>
						<tr>
							<th ng-click="orderByMe('id')">ID</th>
							<th ng-click="orderByMe('name')">Name</th>
							<th ng-click="orderByMe('price')">Price</th>
						</tr>
						<c:forEach var="product" items="${cart}">
							<tr>
								<td>${product.id}</td>
								<td>${product.name}</td>
								<td>${product.price}</td>
							</tr>
						</c:forEach>
					</table>
					<div align="right">
						<a href="orderitems?customerId=" class="btn btn-info"> Order
							Items</a>
					</div>
				</div>
			</c:if>

		</div>
	</div>

</body>
</html>
