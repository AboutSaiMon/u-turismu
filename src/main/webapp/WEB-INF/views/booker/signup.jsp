<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />
<title>Booker Sign Up</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage"></div>
		<div><jsp:include page="../image.jsp" /></div>
		<div id="contentPage">
			<div id="content" class="signup content box">
				<a href="home" class="button right">Home</a>
				<sf:form id="signupForm" action="signup?newBo" method="post" commandName="signup">
					<table>
						<tr>
							<td><label class="label">Email</label></td>
							<td><sf:input disabled="true" path="email" value="${signup.email}" class="fieldIN" /></td>
							<td><sf:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Password</label></td>
							<td><sf:password disabled="true" path="password" value="${signup.password}" class="fieldIN" /></td>
							<td><sf:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Codice Fiscale</label></td>
							<td><sf:input path="taxCode" class="fieldIN" /></td>
							<td><sf:errors path="taxCode" cssClass="error" /></td>
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
									<sf:option value="0" label="--Città di nascità" />
									<sf:options items="${cities}" itemValue="id" itemLabel="name" />
								</sf:select>
							</td>
							<td><sf:errors path="birthPlace" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Data di nascita</label></td>
							<td>
								<sf:select path="birthDay">
									<sf:option value="0" label="--Giorno" />
									<sf:options items="${days}" itemValue="value" itemLabel="label" />
								</sf:select>
								<sf:select path="birthMonth">
									<sf:option value="0" label="--Mese" />
									<sf:options items="${months}" itemValue="value" itemLabel="label" />
								</sf:select>
								<sf:select path="birthYear">
									<sf:option value="0" label="--Anno" />
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
									<sf:option value="0" label="--Seleziona la città" />
									<sf:options items="${cities}" itemValue="id" itemLabel="name" />
								</sf:select>
							</td>
							<td><sf:errors path="city" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Tipo documento</label></td>
							<td>
								<sf:select path="identificationDocumentType">
									<sf:option value="-">--Seleziona il documento</sf:option>
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
					<input type="submit" value="Sign Up" />
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>