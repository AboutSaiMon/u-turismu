
<c:forEach var="pack" items="${packs}">
		<div id="${pack.id}"> <a class="block" href="${pack.id}"> ${pack.name} | ${pack.description} | ${pack.price} </a> </div>
</c:forEach>		
