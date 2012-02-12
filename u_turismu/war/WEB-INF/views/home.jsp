<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home Page</title>
</head>
<body>
	<h1>Home Page</h1>
	<div id="holidayPackages">
		<ul>
			<c:forEach var="holidayPackage" items="${packageList}">
				<li><a href="">${holidayPackage.name} - ${holidayPackage.description}</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>