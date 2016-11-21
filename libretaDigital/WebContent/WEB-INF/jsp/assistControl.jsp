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

	String students = (String) request.getAttribute("students");
	if (students == null) {
		students = "[]";
	}

	String groups = (String) request.getAttribute("groups");
	if (groups == null) {
		groups = "[]";
	}

	String studentsAbsences = (String) request.getAttribute("studentsAbsences");
	if (studentsAbsences == null) {
		studentsAbsences = "[]";
	}
%>

<script type="text/javascript">
	var students =
<%=students%>
	;
	var studentsAbsences =
<%=studentsAbsences%>
	;
	var groups =
<%=groups%>
	;
</script>


<title><%=pageTitle%></title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bower_components/dropzone/dist/basic.css">
<link rel="stylesheet" href="bower_components/angular-percent-circle-directive/dist/percent-circle.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>

<body ng-controller="assistControlCtrl">

	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container" ng-init="">
		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>
						Control de Asistencias <small>Grupo 4°A : Matematicas </small>
						<button id="btn-save" class="btn btn-lg btn-danger" ng-click="saveAbsences()">Guardar</button>
					</h1>
				</div>
				<div class="col-md-4">

					<h1>
						<small class="date-small"> {{date | date:'dd-MM-yyyy'}}</small>

					</h1>
				</div>
			</div>
		</div>

		<div class=row">
			<div class="col-md-8">
				<!-- 				<h3>Lista</h3> -->

				<div class="col-md-6" ng-repeat="list in models">
					<div class="panel {{listType(list.listName)}}" ng-class="">
						<div class="panel-heading">
							<h1 class="panel-title pull-left title-lista">
								{{title(list.listName)}} <span class="badge">{{count(list.listName)}}</span>
							</h1>

							<div class="btn-group pull-right">
								<button id="btn-late" class="btn btn-danger" ng-click="setLate(list.items, list.listName)">
									<span class="glyphicon glyphicon-time"></span>
								</button>
								<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">
									Seleccion <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#" ng-click="selAll(list.items)">Seleccionar Todo</a></li>
									<li><a href="#" ng-click="deSelAll(list.items)">Deseleccionar Todo</a></li>
									<li><a href="#" ng-click="invertirSel(list.items)">Invertir Seleccion</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							
							<div class="clearfix"></div>

						</div>
						<div class="panel-body">

							<button class="btn btn-{{invertlistType(list.listName)}} btn-move"
								ng-click="move(list.items, list.listName)">
								<span class="glyphicon glyphicon-arrow-{{arrowType(list.listName)}}"></span>
							</button>
							<ul dnd-list dnd-drop="onDrop(list, item, index)">
								<li ng-repeat="item in list.items" dnd-draggable="getSelectedItemsIncluding(list, item)"
									dnd-dragstart="onDragstart(list, event)" dnd-moved="onMoved(list)" dnd-dragend="list.dragging = false"
									dnd-selected="item.selected = !item.selected" ng-class="{'selected': item.selected}"
									ng-hide="list.dragging && item.selected"><img class="media-object media-user-list" src="resources/img/nophoto.png" alt="...">{{item.label}} <span id="late-icon" ng-show="item.late"
									class="glyphicon glyphicon-time pull-right" aria-hidden="true"></span></li>
							</ul>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-4">


				<div class="panel-body">
					<div class=row">

						<div class="col-md-6 porcentaje">
							<h3 class="text-center">Asistencia</h3>
							<percent-circle percent="presentsPer" colors="presentsColor"></percent-circle>
						</div>
						<div class="col-md-6 porcentaje">
							<h3 class="text-center">Ausencia</h3>
							<percent-circle percent="absencesPer" colors="absencesColor"></percent-circle>
						</div>
					</div>

					<div class=row">

						<div id="absences-list" class="col-md-12 ">
							<ul class="media-list">
								<li class="media " ng-repeat="student in studentsAbsences">
									<div class="media-left">
										<a href="#"> <img class="media-object media-user" src="resources/img/nophoto.png" alt="...">
										</a>
									</div>
									<div class="media-body">
										<h4 class="media-heading">{{student.label}}</h4>
										Inasistencias {{student.absences}}
									</div>
								</li>
							</ul>
						</div>
					</div>

				</div>


			</div>

		</div>
	</div>



</body>

<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />

<script src="resources/app/controllers/assistControlController.js"></script>
</html>
