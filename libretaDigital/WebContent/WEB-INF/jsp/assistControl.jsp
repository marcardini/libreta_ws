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
<link rel="stylesheet" href="bower_components/angular-percent-circle-directive/dist/percent-circle.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>

<body ng-controller="assistControlCtrl">

	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container" ng-init="">
		<div class="page-header">
			<h1>Control de Asistencia</h1>
		</div>

		<div class=row">
			<div class="col-md-6">
				<!-- 				<h3>Lista</h3> -->

				<div class="col-md-6" ng-repeat="list in models">
					<div class="panel {{listType(list.listName)}}" ng-class="">
						<div class="panel-heading">
							<h1 class="panel-title title-lista">
								{{title(list.listName)}} <span class="badge">{{cantidad(list.listName)}}</span>
								<div class="btn-group btn-lista">
									<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										Acciones <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="#" ng-click="selAll(list.items)">Seleccionar Todo</a></li>
										<li><a href="#" ng-click="deSelAll(list.items)">Deseleccionar Todo</a></li>
										<li><a href="#" ng-click="invertirSel(list.items)">Invertir Seleccion</a></li>
										<li><a href="#">Something else here</a></li>
										<li role="separator" class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>

							</h1>

						</div>
						<div class="panel-body">
							<ul dnd-list dnd-drop="onDrop(list, item, index)">
								<li ng-repeat="item in list.items" dnd-draggable="getSelectedItemsIncluding(list, item)"
									dnd-dragstart="onDragstart(list, event)" dnd-moved="onMoved(list)" dnd-dragend="list.dragging = false"
									dnd-selected="item.selected = !item.selected" ng-class="{'selected': item.selected}"
									ng-hide="list.dragging && item.selected">{{item.label}}</li>
							</ul>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-6">

				<!-- 				<div class="panel panel-default">				 -->
				<div class="panel-body">
					<div class=row">
						<div class="col-md-3 porcentaje">
							<h3 class="text-center">Asistencia</h3>
							<percent-circle percent="presentesPer" colors="presentesColor"></percent-circle>
						</div>
						<div class="col-md-3 porcentaje">
							<h3 class="text-center">Ausencia</h3>
							<percent-circle percent="ausentesPer" colors="ausentesColor"></percent-circle>
						</div>
					</div>
				</div>
				<!-- 				</div> -->
			</div>

		</div>
	</div>



</body>

<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />

<script src="resources/app/controllers/assistControlController.js"></script>
</html>
