<%@page import="uturismu.dto.enumtype.AccountType"%>
<%@page import="uturismu.bean.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/home.css"/>" />

<title>U_Turismu - TourOperator</title>

</head>
<body>
<% 
	AccountBean account = (AccountBean) session.getAttribute("account");
%>
<div id=mainPage>
	<div id="topPage">
		<%
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
		%>
			<jsp:include page="touroperator/topPage.jsp" />
		<%
			} else if (account.getType().equals(AccountType.BOOKER)) {
		%>
			<jsp:include page="booker/topPage.jsp" />
		<%
			}
		%>
	</div>
	
	<jsp:include page="image.jsp" />
	
	<div id="contentPage">
		<%
			if (account.getType().equals(AccountType.TOUR_OPERATOR)) {
		%>
			<jsp:include page="touroperator/homeContent.jsp" />
		<%
			} else if (account.getType().equals(AccountType.BOOKER)) {
		%>
			<jsp:include page="booker/homeContent.jsp" />
		<%
			}
		%>
	</div>
</div>
</body>
</html>