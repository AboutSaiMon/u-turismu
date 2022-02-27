<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="uturismu.dto.enumtype.AccountType"%>
<%@page import="uturismu.bean.AccountBean"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/home.css"/>" /> -->
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<title>uTurismu</title>

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