<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<ul>
		<a id="addPackage" href="newPackage?add=new"><li>Add Holiday Package</li></a>
		<hr>
		<p class="title">Visualizzazione Pacchetti</p> 
	
		<a id="showAll"  href="showPackage?type=ALL"><li>Visualizza Tutti</li></a>
		<a id="showPublished"  href="showPackage?type=PUBLISHED"><li>Visualizza Published</li></a>
		<a id="showDraft"  href="showPackage?type=DRAFT"><li>Visualizza Draft</li></a>
		<a id="showExpired" href="showPackage?type=EXPIRED"><li>Visualizza Expired</li></a>
		
</ul>