<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<script type="text/javascript" src="resources/js/jquery.cycle.all.js"></script>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<title>uTurismu | Home Page</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage">
			<div id="login" class="login">
				<sf:form id="loginForm" action="login" method="post" commandName="login">
					<label class="label">Email</label>
					<sf:input path="loginEmail" cssClass="fieldIN" />
					<sf:errors path="loginEmail" cssClass="fieldIN error" />

					<label class="label">Password</label>
					<sf:password path="loginPassword" cssClass="fieldIN" />
					<sf:errors path="loginPassword" cssClass="fieldIN error" />

					<input type="submit" value="Log In" />
				</sf:form>
			</div>
		</div>

		<div><jsp:include page="image.jsp" /></div>

		<div id="contentPage">
			<div id="signup" class="signup accordion">

				<sf:form id="signupForm" action="signup" method="post" commandName="signup">
					<label>Email</label><br>
					<sf:input path="signupEmail" cssClass="fieldIN" /><br />
					<sf:errors path="signupEmail" cssClass="fieldIN error" /><br />

					<label>Password</label><br />
					<sf:password path="signupPassword" cssClass="fieldIN" /><br />
					<sf:errors path="signupPassword" cssClass="fieldIN error" /><br />

					<label class="label">Booker</label>
					<input type="radio" name="user" value="Booker" checked="checked" />

					<label class="label">Tour Operator</label>
					<input type="radio" name="user" value="Tour Operator" />
					<br />
					<br />

					<input type="submit" value="Sign Up" />
				</sf:form>
			</div>
			<div id="content" class="content">
				<ul>
					<c:forEach var="holiday" items="${holidayList}">
						<li>${holiday.name} (${holiday.description})</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>