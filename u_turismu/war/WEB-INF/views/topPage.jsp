<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div id="access" class="topPage">
	<div id="Loginsfield">
		
			<sf:form method="POST" modelAttribute="credenzial">
				<fieldset>
					<label> E-mail : </label>
					<sf:input path="email" size="20"/>
					<sf:errors path="email" class="error" />
					<label> Password : </label>
					<sf:password path="password" size = "20"/>
					<sf:errors path="password" class="error"/>
					<input type="submit"  value="login"/>
					
				</fieldset>
			</sf:form>
	</div>
</div>

