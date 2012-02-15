<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div id="access" class="topPage">
	<div id="Loginsfield">
		
<%-- 			<sf:form name="form1" method="POST" modelAttribute="credential" action="login"> --%>
				
					<label> E-mail : </label>
					<input id="email" size="20"/>
<%-- 					<sf:errors path="email" class="error" /> --%>
					<label> Password : </label>
					<input type="password" id="password" size = "20"/>
<%-- 					<sf:errors path="password" class="error"/> --%>
<!-- 					<input type="submit" value="login" /> -->
						<input id="button1" type="button" name="buttonlogin" value="login"> 
					
				
<%-- 			</sf:form> --%>
			
			<div id="toFill">
				
			</div>
						
			<script type="text/javascript">
				function write(){
					$("#toFill").append("<p> AIUTO !! </p>");
				}
			
				$("#button1").bind("click",function(){
						write();
						$.postJSON("test",
								{email: $("#email").val().serialize(),password:$("#password").val().serialize() },
								function(data){
									$("#toFill").append("<p> CIAO </p>");
								});
				});
			</script>
	</div>
</div>

