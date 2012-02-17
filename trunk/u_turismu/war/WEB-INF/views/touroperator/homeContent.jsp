<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div id="content" class="content">

	<h1>HOME CONTENT</h1>
	<h1>HOME CONTENT</h1>
	<h1>HOME CONTENT</h1>
	<h1>HOME CONTENT</h1>
	<h1>HOME CONTENT</h1>

	<c:forEach var="pack" items="${packs}">
		<div id="${pack.id}"> <a class="block" href="#${pack.id}"> ${pack.name} | ${pack.description} | ${pack.prize} </a> </div>
	</c:forEach>		
</div>
<div id="menu" class="accordion">
	<ul>
		<a id="addPackage" href="#"><li>Add Holiday Package</li></a>
		<hr>
		<p class="title">Visualizzazione Pacchetti</p> 
	
		<a id="showAll"  href="#"><li>Visualizza Tutti</li></a>
		<a id="showPublished"  href="#"><li>Visualizza Published</li></a>
		<a id="showDraft"  href="#"><li>Visualizza Draft</li></a>
		<a id="showExpired" href="#"><li>Visualizza Expired</li></a>
		
	</ul>
</div>