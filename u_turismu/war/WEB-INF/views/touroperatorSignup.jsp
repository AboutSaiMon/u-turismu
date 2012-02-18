<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<title>Sign Up</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage"></div>
		<div><jsp:include page="image.jsp" /></div>
		<div id="contentPage">
			<div id="signup" class="signup content">
				<sf:form id="signupForm" action="signup?new=touroperator" method="post" commandName="toSignup">
					<table>
						<tr>
							<td><label class="label">Email</label></td>
							<td><sf:input disabled="true" path="email" value="${toSignup.email}" class="fieldIN" /></td>
							<td><sf:errors path="email" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">Password</label></td>
							<td><sf:password disabled="true" path="password" value="${toSignup.password}" class="fieldIN" /></td>
							<td><sf:errors path="password" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">P. IVA</label></td>
							<td><sf:input path="vatNumber" class="fieldIN" /></td>
							<td><sf:errors path="vatNumber" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">Nome Operatore Turistico</label></td>
							<td><sf:input path="name" class="fieldIN" /></td>
							<td><sf:errors path="name" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">Nome e Cognome Titolare</label></td>
							<td><sf:input path="holderName" class="fieldIN" /></td>
							<td><sf:errors path="holderName" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">Via</label></td>
							<td><sf:input path="street" class="fieldIN" /></td>
							<td><sf:errors path="street" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">CAP</label></td>
							<td><sf:input path="zipCode" class="fieldIN" /></td>
							<td><sf:errors path="zipCode" cssClass="fieldIN error" /></td>
						</tr>
						<tr>
							<td><label class="label">Città</label></td>
							<td><sf:select path="city" items="${cities}" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Sign Up" /></td>
						</tr>
					</table>
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>