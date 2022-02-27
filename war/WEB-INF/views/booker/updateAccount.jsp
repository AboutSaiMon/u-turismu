<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<div id="content" class="box bigBox">
				<sf:form id="updateForm" class="center" action="updateBo?doUpdate" method="post" commandName="updateData">
				
					<table>
						<tr>
							<td><label class="label">Email</label></td>
							<td><sf:input disabled="true" path="email" value="${updateData.email}" class="fieldIN" /></td>
							<td><sf:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Password</label></td>
							<td><sf:password disabled="false" path="password" value="${updateData.password}" class="fieldIN" /></td>
							<td><sf:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Codice Fiscale</label></td>
							<td><sf:input path="taxCode" class="fieldIN" /></td>
							<td>
								<sf:errors path="taxCode" cssClass="error" />
								<span class="error">${errMessage}</span>
							</td>
						</tr>
						<tr>
							<td><label class="label">Nome</label></td>
							<td><sf:input path="firstName" class="fieldIN" /></td>
							<td><sf:errors path="firstName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Cognome</label></td>
							<td><sf:input path="lastName" class="fieldIN" /></td>
							<td><sf:errors path="lastName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Sesso</label></td>
							<td>
								<sf:select path="gender">
									<sf:option value="-">--Sesso</sf:option>
									<sf:option value="m">Maschile</sf:option>
									<sf:option value="f">Femminile</sf:option>
								</sf:select>
							</td>
							<td><sf:errors path="gender" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Luogo di Nascita</label></td>
							<td>
								<sf:select path="birthPlace">
									<sf:options items="${cities}" itemValue="id" itemLabel="name" />
								</sf:select>
							</td>
							<td><sf:errors path="birthPlace" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Data di nascita</label></td>
							<td>
								<sf:select path="birthDay">
									<sf:options items="${days}" itemValue="value" itemLabel="label" />
								</sf:select>
								<sf:select path="birthMonth">
									<sf:options items="${months}" itemValue="value" itemLabel="label" />
								</sf:select>
								<sf:select path="birthYear">
									<sf:options items="${years}" itemValue="value" itemLabel="label" />
								</sf:select>
							</td>
						</tr>
						<tr>
							<td><label class="label">Indirizzo</label></td>
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
							<td><sf:errors path="city" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Tipo documento</label></td>
							<td>
								<sf:select path="identificationDocumentType">
									<sf:option value="id">Carta d'Identità</sf:option>
									<sf:option value="pat">Patente</sf:option>
									<sf:option value="pas">Passaporto</sf:option>
									<sf:option value="vis">Permesso (VISA)</sf:option>
								</sf:select>
							</td>
							<td><sf:errors path="identificationDocumentType" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Numero documento</label></td>
							<td><sf:input path="identificationDocumentNumber" class="fieldIN" /></td>
							<td><sf:errors path="identificationDocumentNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Autorità Rilasciante</label></td>
							<td><sf:input path="issuingAuthority" class="fieldIN" /></td>
							<td><sf:errors path="issuingAuthority" cssClass="error" /></td>
						</tr>
					</table>
						<input type="submit" value="Update" class="button right" />
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>