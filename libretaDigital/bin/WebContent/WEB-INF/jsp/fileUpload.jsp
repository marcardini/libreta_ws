<%@ page import="org.codehaus.jackson.*"%>
<%@ page language="java" contentType="text/html; charset=UTF8)" pageEncoding="UTF8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app" flow-init>
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

<body ng-controller="fileUploadCtrl" ng-cloak>

	<div class="container">

		<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />


		<h3>Depósito de archivos</h3>

		<div class="row">
			<div class="col-md-12">
				<div class="">
					<form action="api/upload" class="my-drop-zone drop-zone dropzone" dropzone="" id="dropzone">
						<div class="dz-default dz-message"></div>
						<div class="dz-progress">
							<span class="dz-upload" data-dz-uploadprogress></span>
						</div>
					</form>
					<div class="btn-dropzone">
						<button class="btn btn-success btn-sm" ng-click="uploadFile()">Upload File</button>
						<button class="btn btn-danger btn-sm" ng-click="reset()">Reset Dropzone</button>
					</div>
				</div>
			</div>
		</div>





		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-md-4"> -->
		<!-- 				<h3>Depósito de archivos</h3> -->
		<!-- 				<div class="alert my-drop-zone drop-zone" flow-drop flow-drag-enter="class='alert-success'" flow-drag-leave="class=''" -->
		<!-- 					ng-class="class" flow-prevent-drop flow-drag-enter="style={border: '5px solid green'}" flow-drag-leave="style={}" -->
		<!-- 					ng-style="style"></div> -->
		<!-- 				<br /> <span class="btn-drop" flow-btn><i class="glyphicon glyphicon-file"></i>Seleccionar Archivo</span> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-8" style="margin-bottom: 40px"> -->
		<!-- 				<h3> -->
		<!-- 					Cola de Carga <span class="label label-info" ng-show="$flow.isUploading()">Subiendo</span> -->
		<!-- 				</h3> -->

		<!-- 				<table class="table"> -->
		<!-- 					<thead> -->
		<!-- 						<tr> -->
		<!-- 							<th width="50%">Nombre</th> -->
		<!-- 							<th>Tamaño</th> -->
		<!-- 							<th>Progreso</th> -->
		<!-- 							<th>Estado</th> -->
		<!-- 							<th>Acciones</th> -->
		<!-- 						</tr> -->
		<!-- 					<tbody> -->
		<!-- 						<tr ng-repeat="file in $flow.files"> -->
		<!-- 							<td><strong>{{ file.name }}</strong></td> -->
		<!-- 							<td nowrap>{{ file.size/1024/1024|number:2 }} MB</td> -->
		<!-- 							<td> -->
		<!-- 								<div class="progress" style="margin-bottom: 0;"> -->
		<!-- 									<div class="progress-bar" role="progressbar" ng-style="{ 'width': file.progress() + '%' }"></div> -->
		<!-- 								</div> -->
		<!-- 							</td> -->
		<!-- 							<td class="text-center"><span ng-show="file.isComplete()"><i class="glyphicon glyphicon-ok"></i></span> <span -->
		<!-- 								ng-show="file.error"><i class="glyphicon glyphicon-remove"></i></span></td> -->
		<!-- 							<td> -->
		<!-- 								<div class="btn-group"> -->
		<!-- 									<a class="btn btn-mini btn-warning" ng-click="file.pause()" ng-hide="file.paused"> Pause </a> <a -->
		<!-- 										class="btn btn-mini btn-warning" ng-click="file.resume()" ng-show="file.paused"> Resume </a> <a -->
		<!-- 										class="btn btn-mini btn-danger" ng-click="file.cancel()"> Cancel </a> <a class="btn btn-mini btn-info" -->
		<!-- 										ng-click="file.retry()" ng-show="file.error"> Reintentar </a> -->
		<!-- 								</div> -->
		<!-- 							</td> -->
		<!-- 						</tr> -->
		<!-- 					</tbody> -->
		<!-- 				</table> -->

		<!-- 				<div> -->
		<!-- 					<div> -->
		<!-- 						Progreso: -->
		<!-- 						<div class="progress" style=""> -->
		<!-- 							<div class="progress-bar" role="progressbar" ng-style="{ 'width': file.progress() + '%' }"></div> -->
		<!-- 						</div> -->
		<!-- 					</div> -->
		<!-- 					<button type="button" class="btn btn-success btn-s" ng-click="$flow.resume()"> -->
		<!-- 						<span class="glyphicon glyphicon-upload"></span> Cargar -->
		<!-- 					</button> -->
		<!-- 					<button type="button" class="btn btn-warning btn-s" ng-click="$flow.pause()" ng-disabled="!$flow.paused"> -->
		<!-- 						<span class="glyphicon glyphicon-ban-circle"></span> Pausar -->
		<!-- 					</button> -->
		<!-- 					<button type="button" class="btn btn-danger btn-s" ng-click="$flow.cancel()" ng-disabled="!$flow.isUploading()"> -->
		<!-- 						<span class="glyphicon glyphicon-trash"></span> Cancelar -->
		<!-- 					</button> -->
		<!-- 				</div> -->

		<!-- 			</div> -->

		<!-- 		</div> -->

		<!-- 	</div> -->
</body>

<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />

<script type="text/javascript">
	Dropzone.autoDiscover = false;
</script>
</html>

