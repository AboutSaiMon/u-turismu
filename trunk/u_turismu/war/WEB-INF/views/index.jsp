<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
			<fieldset>
				<label for="usr">Username</label>
				<sf:input id="usr" path="username" />
				<sf:errors path="username" cssClass="error" />
				<label for="pwd">Password</label>
				<sf:password id="pwd" path="password" />
				<sf:errors path="password" cssClass="error" />
				<input type="submit" value="Log In" />
			</fieldset>
		</sf:form>
	</div>
	<div id="packageList">
		
	</div>
	<div id="signup">
	
	</div>
</body>
</html>