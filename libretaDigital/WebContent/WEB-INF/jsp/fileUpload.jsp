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
<link rel="stylesheet" href="bower_components/ng-notify/dist/ng-notify.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css"/>
<link rel="stylesheet" href="resources/css/style.css">
</head>

<body ng-controller="fileUploadCtrl" ng-cloak>

	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container block-ui-main" block-ui="main">

		<div class="page-header">
			<h1>Carga Masiva de Datos</h1>
		</div>

		<div class="row">

			<div class="col-md-4">
				<h3>
					Seleccionar tipo de carga: <select name="selectedType" ng-model="selectedType">
						<option value="PROFESSORS">Profesores</option>
						<option value="STUDENTS">Estudiantes</option>
						<option value="GROUPS">Grupos</option>
					</select><br>
				</h3>
				<form action="api/upload" class="my-drop-zone drop-zone dropzone" dropzone="" id="dropzone">
					<div class="dz-default dz-message"></div>
					<div class="dz-progress">
						<span class="dz-upload" data-dz-uploadprogress></span>
					</div>
				</form>
				<div class="btn-dropzone">
					<button class="btn btn-success btn-sm" ng-click="uploadFile()">Subir Archivo</button>
					<button class="btn btn-danger btn-sm" ng-click="reset()">Limpiar</button>
				</div>
			</div>

			<div class="col-md-8">
				<h3>Información</h3>
				<div class="panel panel-default">
					<div class="panel-body">Información de carga - no disponible</div>
				</div>
			</div>
		</div>

	</div>
</body>

<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
<script src="resources/app/controllers/fileUploadController.js"></script>

<script type="text/javascript">
	Dropzone.autoDiscover = false;
</script>
</html>

