<%@ page import="org.codehaus.jackson.*"%>
<%@ page language="java" contentType="text/html; charset=UTF8)" pageEncoding="UTF8"%>

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
<link rel="stylesheet" href="bower_components/dropzone/dist/basic.css">
<link rel="stylesheet" href="bower_components/dropzone/dist/dropzone.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>

<body ng-controller="assistControlCtrl">



	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container">
		<div class="page-header">
			<h1>Control de Asistencia</h1>
		</div>

		<div class=row">

			<div class="col-md-6">
				<div class="multi-sortable" sv-root>
					<div class="sortable-container" sv-part="data.items6">
						<div sv-helper class="helper-left">custom left helper</div>
						<div ng-repeat="item in data.items6" sv-element class="well multi">{{item}}</div>
					</div>
					<div class="sortable-container" sv-part="data.items7">
						<div ng-repeat="item in data.items7" sv-element class="well multi">
							<div sv-helper class="helper-right">custom right helper {{item}}</div>
							{{item}}
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<h3>Informacion</h3>
				<div class="panel panel-default">
					<div class="panel-body">Panel Content</div>
				</div>
			</div>
			
		</div>
	</div>



</body>

<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />

<script src="resources/app/controllers/assistControlController.js"></script>
</html>
