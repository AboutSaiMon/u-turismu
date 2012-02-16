<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h1>HOME CONTENT</h1>
<div id="packagesList">
	<c:forEach var="pack" items="${packs}">
		<div id="${pack.id}"> <a class="block" href="#${pack.id}"> ${pack.name} | ${pack.description} | ${pack.prize} </a> </div>
	</c:forEach>
			
</div>
<div id="packageFunction" class="accordion">
	<ul>
		<li><a id="addPackage" class="button" href="#">Add Holiday Package</a></li>
		<hr>
		<p class="title">Visualizzazione Pacchetti</p> 
		<li><a id="showAll" class="button" href="#">Visualizza Tutti</a></li>
		<li><a id="showPublished" class="button" href="#">Visualizza Published</a></li>
		<li><a id="showDraft" class="button" href="#">Visualizza Draft</a></li>
		<li><a id="showExpired" class="button" href="#">Visualizza Expired</a></li>
	</ul>
</div>