<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<title> uTurismu | Update Account </title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage"> 
			<jsp:include page="topPage.jsp" />
		</div>
		<div>
			<jsp:include page="../image.jsp" />
		</div>
		<div id="contentPage">
			<div id="content" class="update content bigBox">
				
				<sf:form id="updateForm" class="center" action="updateTo?doUpdate" method="post" commandName="updateData" >
					<table>
						<tr>
							<td><label class="label">Email</label></td>
							<td><sf:input disabled="true" path="email"
									value="${updateData.email}" class="fieldIN" /></td>
							<td><sf:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Password</label></td>
							<td><sf:password disabled="false" path="password"
									value="${updateData.password}" class="fieldIN" /></td>
							<td><sf:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">P. IVA</label></td>
							<td><sf:input path="vatNumber" class="fieldIN" /></td>
							<td>
								<sf:errors path="vatNumber" cssClass="error" />
								<span class="error">${errMessage}</span>
							</td>
						</tr>
						<tr>
							<td><label class="label">Nome Operatore Turistico</label></td>
							<td><sf:input path="name" class="fieldIN" /></td>
							<td><sf:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Nome e Cognome Titolare</label></td>
							<td><sf:input path="holderName" class="fieldIN" /></td>
							<td><sf:errors path="holderName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Via</label></td>
							<td><sf:input path="street" class="fieldIN" /></td>
							<td><sf:errors path="street" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">CAP</label></td>
							<td><sf:input path="zipCode" class="fieldIN" /></td>
							<td><sf:errors path="zipCode" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Città</label></td>
							<td>
								<sf:select path="city">	
									<sf:options items="${cities}" itemValue="id" itemLabel="name" />
								</sf:select>
							</td>
						</tr>
					</table>
					<br />
					<input type="submit" value="Update" class="button right" />
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>