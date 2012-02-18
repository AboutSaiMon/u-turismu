<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<title>Signup</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage">
		</div>
		<div><jsp:include page="image.jsp" /></div>
		<div id="signup">
			<sf:form id="signupForm" action="signup" commandName="signup">
			</sf:form>
		</div>
	</div>
</body>
</html>