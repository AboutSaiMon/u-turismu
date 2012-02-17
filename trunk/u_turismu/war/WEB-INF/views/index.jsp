<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<script type="text/javascript" src="resources/js/jquery.cycle.all.js" ></script>
<script type="text/javascript" src="resources/js/jquery.min.js" ></script>
<!-- <script type="text/javascript" src="resources/js/jquery.js" ></script> -->



<title>uTurismu | Home Page</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage">
			<div id="login" class="login">
				<sf:form id="loginForm" action="home" method="post" modelAttribute="credential">
					<label id="emailLoginLabel" for="emailLogin">Email</label>
					<sf:input id="emailLogin" cssClass="fieldIN" path="email" />
					<sf:errors path="email" cssClass="fieldIN error" />
				
					<label id="passwordLoginLabel" for="passwordLogin">Password</label>
					<sf:password id="passwordLogin" cssClass="fieldIN" path="password" />
					<sf:errors path="password" cssClass="fieldIN error" />
				
					<input type="submit" value="Log In" />
				</sf:form>
			</div>
		</div>
		
		
		<div><jsp:include page="image.jsp" /></div>
		
		<div id="contentPage">
			<div id="signup" class="signup accordion">
				<sf:form id="signupForm" action="home" method="post" modelAttribute="signup">
				
					<h2> Sign Up </h2>
			
					<label id="emailSignupLabel" for="emailSignup">Email</label>
					<br>
					<sf:input id="emailSignup" cssClass="fieldIN" path="email" />
					<sf:errors path="email" cssClass=" fieldIN error" />
					<br>
					<br>
					<label id="passwordSignupLabel" for="passwordSignup">Password</label>
					<br>
					<sf:password id="passwordSignup" cssClass="fieldIN" path="password" />
					<sf:errors path="password" cssClass="fieldIN error" />
					<br>
					<br>
					<label id="bookerLabel" for="booker">Booker</label>
					<input id="booker" type="radio" name="type" value="booker" checked="checked" />
					<label id="touroperatorLabel" for="touroperator"> TourOperator</label>
					<input id="touroperator" type="radio" name="type" value="touroperator" />
					<br><br>
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