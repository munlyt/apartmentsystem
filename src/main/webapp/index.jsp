<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="processLogin" method="post">
	<c:if test="${not empty error}">
		<div class="alert">
			<spring:message
				code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
			<br />
		</div>
	</c:if>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th>Username</th>
			<td><input type="text" name="username" class="login-inp"
				 /></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="password"
				 class="login-inp" /></td>
		</tr>
		<tr>
			<th></th>
			<td><input type="submit" class="submit-login" /></td>
		</tr>
	</table>
</form>