<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="pack" items="${packs}">
		<div id="${pack.id}" class="packageButton"> 
			<a class="block" href="${pack.id}"> ${pack.name} | ${pack.description} | ${pack.price} </a> 
		</div>
</c:forEach>		
