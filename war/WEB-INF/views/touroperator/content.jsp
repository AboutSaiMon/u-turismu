<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<c:forEach var="holiday" items="${holidayList}">
		<li>
		<form action="${holiday.name}.details" method="post">
			<input type="hidden" name="id" value="${holiday.id}" />
			<input class="button bigButton" type="submit" value="${holiday.name} - (${holiday.price} euro) (${holiday.status})" />
		</form>
		</li>
	</c:forEach>
</ul>