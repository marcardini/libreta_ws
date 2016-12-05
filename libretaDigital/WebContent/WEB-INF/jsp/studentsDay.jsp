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

	String students = (String) request.getAttribute("students");
	if (students == null) {
		students = "[]";
	}
%>

<script type="text/javascript">
	var students =
<%=students%>
	
</script>

<title><%=pageTitle%></title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bower_components/ng-notify/dist/ng-notify.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css">
<link rel="stylesheet" href="bower_components/angular-percent-circle-directive/dist/percent-circle.css">
<link rel="stylesheet" href="resources/css/style.css">

</head>

<body ng-controller="studentsDayCtrl">
	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />


	<div class="container block-ui-main" block-ui="main">

		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>Matematicas Grupo 4°A</h1>
				</div>
				<div class="col-md-4">
					<h1>
						<small class="date-small"> {{date | date:'dd-MM-yyyy'}}</small>
					</h1>
					<!-- 					<h2> -->
					<!-- 						<button id="btn-save" class="btn btn-lg btn-danger" ng-click="">Guardar</button> -->
					<!-- 					</h2> -->
				</div>

			</div>
		</div>

		<div class=row">
			<div class="col-md-3">

				<table st-table="studentsDisplayed" st-safe-src="students" class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th class="sort-header" st-sort="name">Nombre</th>
							<th class="sort-header" st-sort="lastName">Apellido</th>
						</tr>
					</thead>
					<tbody>
						<tr class="list-row" st-select-row="row" st-select-mode="single" ng-repeat="row in studentsDisplayed"
							ng-click="studentSelect(row)">
							<td><img class="media-object media-user-list media-user-medium" src="resources/img/nophoto.png"
								alt="..."></td>
							<td>{{row.name | capitalize}}</td>
							<td>{{row.lastName | capitalize}}</td>
							<!-- 							<td><a ng-href="mailto:{{student.email}}">email</a></td> -->
						</tr>
					</tbody>
				</table>

			</div>

			<div class="col-md-9">

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Información del Estudiante</h3>
					</div>
					<div class="panel-body">

						<div class="col-md-3 col-lg-3 " align="center">
							<img alt="User Pic" src="resources/img/nophoto.jpg" class="img-circle img-responsive">
						</div>
						<div class=" col-md-9 col-lg-9 ">
							<uib-tabset type="pills" justified="true"> <uib-tab heading="Datos">
							<div class="row">
								<div class="col-lg-6">
									<table class="table table-user">
										<tbody>
											<tr>
												<td>Nombre</td>
												<td><strong>{{student.name | capitalize}}</strong></td>
											</tr>
											<tr>
												<td>Apellido</td>
												<td><strong>{{student.lastName | capitalize}}</strong></td>
											</tr>
											<tr>
												<td>Grupo</td>
												<td>{{student.group}}</td>
											</tr>
											<tr>
												<td>Genero</td>
												<td>{{student.gender | capitalize}}</td>
											</tr>
											<tr>
												<td>Dirección</td>
												<td></td>
											</tr>
											<tr>
												<td>Email</td>
												<td><a href="mailto:>{{student.email}}">{{student.email}}</a></td>
											</tr>
											<td>Teléfono</td>
											<td>123-4567-890(Landline)<br>555-4567-890(Mobile)
											</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-lg-6">
									<div class="row">
										<div class="col-lg-6">
											<div class="porcentaje">
												<h3 class="text-center">Promedio</h3>
												<percent-circle percent="student.qualyPer" colors="qualyColor"></percent-circle>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="porcentaje">
												<h3 class="text-center">Faltas</h3>
												<percent-circle percent="student.absencesPer" colors="absencesColor"></percent-circle>
											</div>
										</div>
									</div>
								</div>
							</div>
							</uib-tab> 
							
							<uib-tab heading="Asistencias">


							<div class="row">
								<div class="col-lg-12 table-nav">
									<table st-table="absencesDisplayed" st-safe-src="student.absences" class="table table-striped">
									<thead>
										<tr>
											<th class="sort-header" st-sort="name">Fecha</th>
											<th class="sort-header" st-sort="lastName">Tipo</th>
											<th>Comentario</th>
										</tr>
									</thead>
									<tbody>
										<tr class="list-row" st-select-row="absence" st-select-mode="single"
											ng-repeat="absence in absencesDisplayed" ng-click="absenceSelect(absence)">
											<td>{{absence.date}}</td>
											<td>{{absence.label | capitalize}}</td>
											<td>{{absence.comment | capitalize}}</td>
										</tr>
									</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<button class="btn btn-md btn-success btn-justify" ng-click="" ng-disabled="editButtons">Justificar</button>
									<button class="btn btn-md btn-danger btn-justify" ng-click="" ng-disabled="editButtons">Eliminar</button>								
								</div>
							</div>
							
							</uib-tab>
							
							 <uib-tab heading="Calificaciones">

							<div class="row">
								<div class="col-lg-12 table-nav">
									<table st-table="qualificationsDisplayed" st-safe-src="student.qualifications"
										class="table table-striped">
										<thead>
											<tr>
												<th class="sort-header" st-sort="name">Fecha</th>
												<th class="sort-header" st-sort="lastName">Tipo</th>
												<th class="sort-header" st-sort="value">Nota</th>
												<th>Comentario</th>
											</tr>
										</thead>
										<tbody>
											<tr class="list-row" st-select-row="qualy" st-select-mode="single"
												ng-repeat="qualy in qualificationsDisplayed" ng-click="qualySelect(qualy)">
												<td>{{qualy.date}}</td>
												<td>{{qualy.eventRegistrationType | capitalize}}</td>
												<td>{{qualy.value}}</td>
												<td>{{qualy.comment | capitalize}}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>


							<div class="row">
								<div class="col-lg-12">
									<button class="btn btn-md btn-success btn-justify" ng-click="" ng-disabled="editButtons">Calificar</button>								
								</div>
							</div>

							</uib-tab> </uib-tabset>


							<!-- 								<a href="#" class="btn btn-primary">My Sales Performance</a> <a href="#" class="btn btn-primary">Team -->
							<!-- 									Sales Performance</a> -->
						</div>
					</div>
				</div>


				<!-- 					<div class="panel-footer text-right">						 -->
				<!-- 						<button  class="btn btn-md btn-danger" ng-click="">Guardar</button>  -->
				<!-- 					</div> -->

			</div>

		</div>

	</div>


	</div>
</body>





<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />

<script src="resources/app/controllers/studentsDayController.js"></script>
</html>
