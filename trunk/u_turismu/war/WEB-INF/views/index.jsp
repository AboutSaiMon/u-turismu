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
<title>uTurismu | Home Page</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage">
			<div id="login" class="login">
				<sf:form id="loginForm" action="home" method="post" commandName="credential">
					<label id="emailLabel" for="email">Email</label>
					<sf:input id="email" cssClass="fieldIN" path="email" />
					<sf:errors path="email" cssClass="fieldIN error" />
				
					<label id="passwordLabel" for="password">Password</label>
					<sf:password id="password" cssClass="fieldIN" path="password" />
					<sf:errors path="password" cssClass="fieldIN error" />
				
					<input type="submit" value="Log In" />
				</sf:form>
			</div>
		</div>
		
		<div><jsp:include page="image.jsp" /></div>
		
		<div id="contentPage">
			<div id="signup" class="signup accordion">
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