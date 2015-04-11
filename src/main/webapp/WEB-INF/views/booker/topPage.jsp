<%@page import="uturismu.bean.BookerBean"%>
<% 
	BookerBean account = (BookerBean) session.getAttribute("account");
%>
<div id="accountData">
	<span class="marker"> 
		Booker: <%=account.getFirstName()%> <%=account.getLastName()%> 
	 </span>
	 <span class="marker right"> 
		(<%=account.getEmail()%>) 
	 </span>
</div>
<div id="accountFunction">
	<a id="accountModify" class="button" href="updateBo">Modifica Account</a>
	<a id="accountLogOut" class="button" href="logout">Log Out</a>
</div>