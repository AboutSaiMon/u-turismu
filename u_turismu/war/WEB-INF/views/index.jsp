<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.error {
	color: red;
}
</style>
<title>uTurismu</title>
</head>
<body>
	<div id="login">
		<sf:form id="loginForm" action="home" method="post"
			modelAttribute="credential">
			<label for="email">Email</label>
			<sf:input id="email" path="email" />
			<sf:errors path="email" cssClass="error" />
			<label for="password">Password</label>
			<sf:password id="password" path="password" />
			<sf:errors path="password" cssClass="error" />
			<input type="submit" value="Log In" />
		</sf:form>
	</div>
	<div id="packageList">
		<span>Scegli la tua vacanza</span>
		<ul>
			<c:forEach var="holiday" items="${holidayList}">
				<li>${holiday.name} (${holiday.description})</li>
			</c:forEach>
		</ul>
	</div>
	<div id="signup">
		<sf:form id="signupForm" method="post"
			modelAttribute="accountRegistration">
			<table id="signupTable">
				<tr>
					<td><label for="emailReg">Email</label></td>
					<td><sf:input id="emailReg" path="email" /></td>
				</tr>
				<tr>
					<td><label for="passwordReg">Password</label></td>
					<td><sf:password id="passwordReg" path="password" />
				</tr>
				<tr>
					<td><label for="tourOperator">Tour Operator</label></td>
					<td><sf:radiobuttons path="tourOperator" /> </td>
				</tr>
				<tr>
					<td><label for="booker">Booker</label></td>
					<td><sf:radiobutton id="booker" path="booker" /></td>
				</tr>
			</table>
		</sf:form>
	</div>
</body>
</html>