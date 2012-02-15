<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home Page</title>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li>Home</li>
				<li>Chi siamo</li>
				<li>Contattaci</li>
			</ul>
		</nav>
	</header>
	<jsp:include page="${page}" />
	<a href="registration"></a>
</body>
</html>