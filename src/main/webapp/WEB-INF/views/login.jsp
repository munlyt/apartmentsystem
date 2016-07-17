<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/jquery-2.0.3.min.js"></script>
<script src="resources/js/angular.js"></script>
<title>Cubic Apartment System : Login</title>
</head>
<body>
	<div class="alert alert-info">Cubic Apartment System | Login</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div ng-app="myApp" class="middle">
					<c:if test="${not empty error}">
						<div class="alert alert-danger">
							<div class="alert">
								<spring:message
									code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
								<br />
							</div>
						</div>
					</c:if>
					<form name="form" action="processLogin" method="post">
						<div class="form-group">
							<label for="username">Username</label> <i class="fa fa-key"></i>
							<input type="text" name="username" id="username"
								class="form-control" ng-model="username" required /> <span
								ng-show="form.username.$dirty && form.username.$error.required"
								class="help-block">Username is required</span>
						</div>
						<div class="form-group">
							<label for="password">Password</label> <i class="fa fa-lock"></i>
							<input type="password" name="password" id="password"
								class="form-control" ng-model="password" required /> <span
								ng-show="form.password.$dirty && form.password.$error.required"
								class="help-block">Password is required</span>
						</div>
						<div class="form-actions">
							<button type="submit" ng-disabled="form.$invalid || dataLoading"
								class="btn btn-danger">Login</button>
							<img ng-if="dataLoading"
								src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
						</div>
					</form>
				</div>
			</div>
			<div class="col-lg-4"></div>
		</div>
	</div>

	<script>
		var app = angular.module("myApp", []);
	</script>
</body>
</html>