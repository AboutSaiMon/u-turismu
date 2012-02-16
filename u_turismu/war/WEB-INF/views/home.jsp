<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css/home.css"/>" />


<title>U_Turismu - TourOperator</title>

</head>
<body>
	<div id="topPage">
		<div id="accountData"></div>
		<div id="accountFunction">
			<a id="accountModify" class="button" href="#">Modifica Account</a> <a
				id="accountLogOut" class="button" href="#">Log Out</a>
		</div>
	</div>

	<div id="contentPage">
		<jsp:include page="${content}" />
	</div>
</body>
</html>