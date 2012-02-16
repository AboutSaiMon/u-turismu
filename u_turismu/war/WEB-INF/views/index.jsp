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
		<sf:form id="loginForm" action="home" method="post" modelAttribute="credential">
			<label id="emailLoginLabel" for="emailLogin">Email</label>
			<sf:input id="emailLogin" path="email" />
			<sf:errors path="email" cssClass="error" />
			<label id="passwordLoginLabel" for="passwordLogin">Password</label>
			<sf:password id="passwordLogin" path="password" />
			<sf:errors path="password" cssClass="error" />
			<input type="submit" value="Log In" />
		</sf:form>
	</div>
	<div id="signup">
		<sf:form id="signupForm" action="home" method="post" modelAttribute="signup">
			<label id="emailSignupLabel" for="emailSignup">Email</label>
			<sf:input id="emailSignup" path="email" />
			<sf:errors path="email" cssClass="error" />
			<label id="passwordSignupLabel" for="passwordSignup">Password</label>
			<sf:password id="passwordSignup" path="password" />
			<sf:errors path="password" cssClass="error" />
			<label id="bookerLabel" for="booker">Booker</label>
			<input id="booker" type="radio" name="type" value="booker" checked="checked" />
			<label id="touroperatorLabel" for="touroperator">Tour Operator</label>
			<input id="touroperator" type="radio" name="type" value="touroperator" />
			<input type="submit" value="Sign Up" />
		</sf:form>
	</div>
	<div id="packageList">
		<ul>
			<c:forEach var="holiday" items="${holidayList}">
				<li>${holiday.name} (${holiday.description})</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>