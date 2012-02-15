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
			modelAttribute="tourOperatorAccount">
			<table id="signupTable">
				<tr>
					<td><label for="email">Email</label></td>
					<td><sf:input id="email" path="email" /></td>
				</tr>
				<tr>
					<td><label for="pwd">Password</label></td>
					<td><sf:password id="pwd" path="password" />
				</tr>
			</table>
		</sf:form>
	</div>
</body>
</html>