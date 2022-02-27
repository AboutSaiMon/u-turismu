<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="resources/css/home.css" />

<title>uTurismu | Add Package</title>
</head>
<body>
	<div id="mainPage">
		<div id="topPage">
			<jsp:include page="../touroperator/topPage.jsp" />
		</div>
		<div>
			<jsp:include page="../image.jsp" />
		</div>
		<div id="contentPage">
			<div id="content" class="box bigBox">
				<sf:form id="addPackageForm" class="left" action="addPackage?doAdd" method="post" commandName="addData">
					<input type="submit" value="Salva Pacchetto" class="button right" />
					<table>
						<tr>
							<td><label class="label">Nome Pacchetto</label></td>
							<td><sf:input path="name" value="" class="fieldIN" /></td>
							<td><sf:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Descrizione </label></td>
							<td><sf:textarea path="description" value="" class="" rows="10" cols="60" /></td>
							<td><sf:errors path="description" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Numero Persone</label></td>
							<td><sf:input path="customerNumber" value="" class="fieldIN" /></td>
							<td><sf:errors path="customerNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label class="label">Numero Pacchetti</label></td>
							<td><sf:input path="availability" value="" class="fieldIN" /></td>
							<td><sf:errors path="availability" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label class="label">Data di Scadenza</label></td>
							<td> 
								<sf:select path="day">
									<sf:options items="${days}" itemValue="value" itemLabel="label" />
								</sf:select>
								<sf:select path="month">
									<sf:options items="${months}" itemValue="value" itemLabel="label" />
								</sf:select>
								<sf:select path="year">
									<sf:options items="${years}" itemValue="value" itemLabel="label" />
								</sf:select>
							</td>
						</tr>
						
						<tr>
							<td><label class="label">Stato del Pacchetto</label></td>
							<td>
								<sf:select path="status">
									<sf:option value="draft" label="BOZZA" />
									<sf:option value="pub" label="PUBBLICO" />
								</sf:select>
							</td>
						</tr>
					</table>
				</sf:form>
			</div>
		</div>
	</div>
</body>
</html>