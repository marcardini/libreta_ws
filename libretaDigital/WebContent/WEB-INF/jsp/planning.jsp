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
								<label for="descr">Descripción:</label>
								<textarea class="form-control vresize" rows="10" id="comment" ng-model="selectedDay.text"></textarea>
							</div>
						</form>
					</div>

					<div class="col-md-3">
						
						<table st-table="desarrolloDisplayed" st-safe-src="desarrolloDelCurso" class="table table-striped table-hover">
					<thead >
						<tr>
							<th colspan="6"><input st-search="" class="form-control" placeholder="búsqueda rápida ..." type="text" /></th>
						</tr>
						<tr>
<!-- 							<th></th> -->
							<th class="sort-header text-center" st-sort="date" class="text-center">Fecha</th>
<!-- 							<th class="sort-header text-left" st-sort="text" class="text-left">Apellido</th> -->
<!-- 							<th class="sort-header text-left" st-sort="email" class="text-left">Email</th> -->
<!-- 							<th></th> -->
						</tr>

					</thead>
					<tbody>
						<tr ng-repeat="row in desarrolloDisplayed" st-select-row="row" st-select-mode="single"  class="list-row text-left"
							ng-click="rowSelect(row)">
<!-- 							<td><img class="media-object media-user-list media-user-medium" src="resources/img/nophoto.png" -->
<!-- 								alt="..."></td> -->
							<td>{{row.date}}</td>
<!-- 							<td>{{row.lastName | capitalize}}</td> -->
<!-- 							<td>{{row.email | capitalize}}</td> -->
<!-- 							<td> -->
<!-- 								<button type="button" confirmed-click="deleteStudent(row.oid)" class="btn btn-danger" ng-confirm-click="Esta seguro que desea eliminar este estudiante?"> -->
<!--  									<i class="glyphicon glyphicon glyphicon-remove"></i> -->
<!-- 								</button> -->
<!-- 							</td> -->
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5" class="text-center">
								<div st-pagination="" st-items-by-page="10" st-displayed-pages="5"></div>
							</td>
						</tr>
					</tfoot>
				</table>
					
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
