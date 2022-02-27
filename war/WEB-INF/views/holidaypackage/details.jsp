<%@page import="uturismu.dto.enumtype.Status"%>
<%@page import="uturismu.controller.util.SessionCheck"%>
<%@page import="uturismu.bean.AccountBean"%>
<%@page import="uturismu.bean.HolidayPackageBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<div class="holiday-content">
	<%
		HolidayPackageBean bean = (HolidayPackageBean) request.getAttribute("holiday");
		if (SessionCheck.isBooker(session)) {
	%>
	<form action="book" method="post">
		<input type="hidden" name="id" value="${holiday.id}" /> <input
			class="button right" type="submit" value="Prenota" />
	</form>
	<%
		} else if (SessionCheck.isTourOperator(session) && bean.getStatus().equals(Status.DRAFT)) {
	%>
	<form action="publish" method="post">
		<input type="hidden" name="id" value="${holiday.id}" /> <input
			class="button right" type="submit" value="Pubblica" />
	</form>
	<%
		} else if (!SessionCheck.isActiveSession(session)) {
	%>
	<a href="home" class="button right">Torna alla Home</a>
	<%
		}
	%>
	<div>
		<div class="holidayTitle">${holiday.name}</div>
		<%
			if (!SessionCheck.isTourOperator(session)) {
		%>
		<div class="holidayTouroperator">${holiday.tourOperatorName} di
			${holiday.tourOperatorHolderName} (${holiday.tourOperatorEmail})</div>
		<div class="holidayDescription">${holiday.description}</div>
		<%
			}
		%>
		<div class="holidayDetails">
			<div>
				<span class="property">Numero Persone:</span> <span class="value">${holiday.customerNumber}</span>
			</div>
			<%
				if (SessionCheck.isTourOperator(session)) {
			%>
			<div>
				<span class="property">Pacchetti Totali:</span> 
				<span class="value">${holiday.availability}</span>
			</div>
			<%
				}
				Integer counter = bean.getCounter();
				Integer available = bean.getAvailability();
				Integer remaining = available - counter;
			%>
			<div>
				<span class="property">Pacchetti Disponibili:</span> <span
					class="value"><%=remaining%></span>
			</div>
			<%
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
				Date date = bean.getDueDate();
			%>
			<div>
				<span class="property">Data Scadenza</span> <span class="value"><%=formatter.format(date)%></span>
			</div>
			<%
				if (SessionCheck.isTourOperator(session)) {
			%>
			<div>
				<span class="property">Status</span> <span class="value">${holiday.status}</span>
			</div>
			<%
				}
			%>
		</div>
	</div>
</div>