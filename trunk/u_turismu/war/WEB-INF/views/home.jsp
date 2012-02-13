<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home Page</title>
</head>
<body>
	<div id="top"> <%@include file="topPage.jsp"  %> </div>
	<h1>Home Page</h1>
	<div id="holidayPackages">
		<ul>
			<c:forEach var="holidayPackage" items="${packageList}">
				<li><a href="holidayDetail.jsp?prova=ciao">${holidayPackage.name} - ${holidayPackage.description}</a></li>
				<s:url value=""></s:url>
			</c:forEach>
		</ul>
	</div>
</body>
</html>