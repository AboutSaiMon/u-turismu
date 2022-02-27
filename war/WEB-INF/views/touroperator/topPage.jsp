<%@page import="uturismu.bean.TourOperatorBean"%>
<%@page import="uturismu.bean.AccountBean"%>

<% 
	TourOperatorBean account = (TourOperatorBean) session.getAttribute("account");
%>
<div id="accountData">
	<span class="marker">
		Tour Operator: <%=account.getName()%> 
	</span>
	<span class="marker right">
		(<%=account.getEmail()%>) 
	</span>
</div>

<div id="accountFunction">
	<a href="home" class="button">Home</a>
	<a id="accountModify" class="button" href="updateTo">Modifica Account</a> 
		<a id="accountLogOut" class="button" href="logout">Log Out</a>
</div>