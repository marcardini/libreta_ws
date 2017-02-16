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

	String studentNotas = (String) request.getAttribute("studentNotas");
	if (studentNotas == null) {
		studentNotas = "[]";
	}
%>


<script type="text/javascript">
	var studentNotas =
<%=studentNotas%>
	;
</script>

<title><%=pageTitle%></title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bower_components/ng-notify/dist/ng-notify.min.css">
<link rel="stylesheet"
	href="bower_components/angular-bootstrap-calendar/dist/css/angular-bootstrap-calendar.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css" />
<link rel="stylesheet" href="resources/css/style.css">

</head>


<body ng-controller="bulletinCtrl">

	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container block-ui-main" block-ui="main">

		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>
						Boletines <small></small>
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
			<div class="col-md-3">

				<table st-table="studentsDisplayed" st-safe-src="studentNotas" class="table table-striped table-hover">
					<thead>
						<tr>
							<th></th>
							<th class="sort-header" st-sort="name">Nombre</th>
							<th class="sort-header" st-sort-default="true" st-sort="lastName">Apellido</th>
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

				<div class="row">
					<div class="col-md-3">
						<div class="" align="center">
							<img alt="User Pic" src="resources/img/nophoto.jpg" class="img-circle img-responsive img-bulletin">
						</div>
					</div>
					<div class="col-md-9">
						<h1>{{student.name + " " + student.lastName | capitalize}}</h1>
						<h1>Grupo {{student.groupCode}}</h1>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-bulletin">
						<div class="panel panel-default panel-bulletin">
							<div class="panel-heading text-center">Primera Reunion</div>

							<table class="table table-user">
								<thead>
									<tr>
										<th class="text-center"></th>
										<th class="text-center">Promedio</th>
										<th class="text-center">Comentario</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="sub in student.subjects">
										<td>{{sub.subjectName | capitalize}}</td>
										<td class="text-center">{{ppr(sub.calendar)}}</td>
										<td class="text-center">{{pjd(sub.calendar)}}</td>
									</tr>
								</tbody>


							</table>
						</div>
					</div>
					<div class="col-md-2 col-bulletin">
						<div class="panel panel-default panel-bulletin">
							<div class="panel-heading text-center">Segunda Reunion</div>

							<table class="table table-user">
								<thead>
									<tr>
										<th class="text-center">Promedio</th>
										<th class="text-center">Comentario</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="sub in student.subjects">

										<td class="text-center">{{psr(sub.calendar)}}</td>
										<td class="text-center">{{sjd(sub.calendar)}}</td>
									</tr>
								</tbody>


							</table>
						</div>
					</div>
					<div class="col-md-2 col-bulletin">
						<div class="panel panel-default panel-bulletin">
							<div class="panel-heading text-center">Boletin Final</div>

							<table class="table table-user">
								<thead>
									<tr>
										<th class="text-center">Promedio</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="sub in student.subjects">
										<td class="text-center">{{pf(sub.calendar)}}</td>
									</tr>
								</tbody>


							</table>
						</div>

					</div>
					<div class="col-md-4 col-bulletin">
						<div class="panel panel-default panel-bulletin">
							<div class="panel-heading text-center">Inasistencias</div>

							<table class="table table-user">
								<thead>
									<tr>

										<th class="text-center">Llegadas Tarde</th>
										<th class="text-center">Justificadas</th>
										<th class="text-center">Totales</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="sub in student.subjects">
										<td class="text-center">{{llt(sub.calendar)}}</td>
										<td class="text-center">{{fj(sub.calendar)}}</td>
										<td class="text-center">{{ft(sub.calendar)}}</td>
									</tr>
								</tbody>


							</table>
						</div>

					</div>
				</div>

<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-6 col-bulletin"> -->
<!-- 						<div class="panel panel-default panel-bulletin"> -->
<!-- 							<div class="panel-heading text-center">JUICIO DEL DOCENTE</div> -->
<!-- 							<div class="panel-body">{{jd(sub.calendar)}}</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-6 col-bulletin"> -->
<!-- 						<div class="panel panel-default panel-bulletin"> -->
<!-- 							<div class="panel-heading text-center">JUICIO REUNION</div> -->
<!-- 							<div class="panel-body">{{jr(sub.calendar)}}</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->

<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-12 col-bulletin"> -->
<!-- 						<div class="panel panel-default panel-bulletin"> -->
<!-- 							<div class="panel-heading text-center">JUICIO FINAL</div> -->
<!-- 							<div class="panel-body">{{jf(sub.calendar)}}</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>

		</div>

	</div>
</body>




<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
<script src="resources/app/controllers/bulletinController.js"></script>

</html>
