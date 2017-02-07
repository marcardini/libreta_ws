<%@ page import="org.codehaus.jackson.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String pageTitle = (String) request.getAttribute("tituloPagina");
	if (pageTitle == null) {
		pageTitle = "Libreta Digital";
	}
%>
<title><%=pageTitle%></title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="bower_components/ng-notify/dist/ng-notify.min.css"> -->
<!-- <link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css" /> -->
<link rel="stylesheet" href="/resources/css/style.css">
</head>

<body ng-controller="fileUploadCtrl" nv-file-drop="" uploader="uploader"
	filters="queueLimit, customFilter">
	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container">



		<br> <br>
		<div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align: center;">

			Spring MCV Tutorial by <a href="http://crunchify.com">Crunchify</a>. Click <a
				href="http://crunchify.com/category/java-web-development-tutorial/" target="_blank">here</a> for all Java
			and <a href='http://crunchify.com/category/spring-mvc/' target='_blank'>here</a> for all Spring MVC, Web
			Development examples.<br>
		</div>
	</div>
</body>

<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
</html>
