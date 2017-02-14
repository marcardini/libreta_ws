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

	String notebook = (String) request.getAttribute("notebook");
	if (notebook == null) {
		notebook = "[]";
	}
%>

<script type="text/javascript">
	var notebook =
<%=notebook%>
	;
</script>

<title><%=pageTitle%></title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bower_components/ng-notify/dist/ng-notify.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css">
<link rel="stylesheet" href="bower_components/angular-percent-circle-directive/dist/percent-circle.css">
<link rel="stylesheet" href="bower_components/angularjs-slider/dist/rzslider.min.css" />
<link rel="stylesheet" href="resources/css/style.css">

</head>

<body>

	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container block-ui-main" block-ui="main" ng-controller="planningCtrl">

		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>
						Planeamiento<small></small>
						<button id="btn-save" class="btn btn-lg btn-success" ng-click="save()">Guardar</button>
					</h1>
				</div>
				<div class="col-md-4">

					<h1>
						<small class="date-small"> {{date | date:'dd-MM-yyyy'}}</small>

					</h1>
				</div>
			</div>
		</div>

		<div class="content-wrapper">

			<uib-accordion>

			<div uib-accordion-group class="panel-primary" is-open="true" template-url="">
				<uib-accordion-heading>DESARROLLO DEL CURSO<i class="pull-right glyphicon"
					ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
				</uib-accordion-heading>

				<div class="row">

					<div class="col-md-9">
						<form id="idform" role="form" novalidate="" name="psdForm" class="text-left">
							<div class="form-group">
<!-- 								<label for="descr">Descripción:</label> -->
								<textarea class="form-control vresize" rows="14" id="comment" ng-model="day.comment"></textarea>
							</div>
						</form>
					</div>

					<div class="col-md-3 text-right">
						<div style="display: inline-block;">
							<div uib-datepicker ng-model="date" class="well well-sm" datepicker-options="options" ng-click="setDay(date)"></div>
							<button id="btn-save text-right" class="btn btn-sm btn-success" ng-click="addDay(day)" ng-show="isNew">Agregar</button>
							<button id="btn-save text-right" class="btn btn-sm btn-warning" ng-click="addDay(day)" ng-show="!isNew">Modificar</button>
							<button id="btn-save text-right" class="btn btn-sm btn-danger" ng-click="deleteDay()" ng-show="!isNew">Eliminar</button>
						</div>					
						
					</div>

				</div>
			</div>

			<div uib-accordion-group class="panel-primary" is-open="" template-url="">
				<uib-accordion-heading>PAUTA SALA DOCENTE<i class="pull-right glyphicon"
					ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
				</uib-accordion-heading>
				<form id="idform" role="form" novalidate="" name="psdForm" class="text-left">
					<div class="form-group">
						<!-- 									<label for="comment"></label> -->
						<textarea class="form-control vresize" rows="10" id="comment" ng-model="notebook.pautaSalaDocente"></textarea>
					</div>
				</form>
			</div>

			<div uib-accordion-group class="panel-primary" is-open="" template-url="">
				<uib-accordion-heading>PROPUESTA DIAGNOSTICA<i class="pull-right glyphicon"
					ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
				</uib-accordion-heading>
				<form id="pdform" role="form" novalidate="" name="pdForm" class="text-left">
					<div class="form-group">
						<!-- 									<label for="comment"></label> -->
						<textarea class="form-control vresize" rows="10" id="comment" ng-model="notebook.propuestaDiagnostica"></textarea>
					</div>
				</form>
			</div>

			<div uib-accordion-group class="panel-primary" is-open="" template-url="">
				<uib-accordion-heading>DESCRIPCIÓN Y ANALISIS DE RESULTADOS<i
					class="pull-right glyphicon"
					ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
				</uib-accordion-heading>
				<form id="dyaform" role="form" novalidate="" name="dyaForm" class="text-left">
					<div class="form-group">
						<!-- <label for="comment"></label> -->
						<textarea class="form-control vresize" rows="10" id="comment" ng-model="notebook.descripcionYAnalisis"></textarea>
					</div>
				</form>
			</div>

			<div uib-accordion-group class="panel-primary" is-open="" template-url="">
				<uib-accordion-heading>PROGRAMA Y PAUTAS PARA EL EXAMEN<i
					class="pull-right glyphicon"
					ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
				</uib-accordion-heading>
				<form id="dyaform" role="form" novalidate="" name="dyaForm" class="text-left">
					<div class="form-group">
						<!-- <label for="comment"></label> -->
						<textarea class="form-control vresize" rows="10" id="comment" ng-model="notebook.programaYPautaExamen"></textarea>
					</div>
				</form>
			</div>

			</uib-accordion>
		</div>


	</div>
</body>




<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
<script src="resources/app/controllers/planningController.js"></script>

</html>
