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
<link rel="stylesheet" href="resources/css/style.css">

</head>

<body ng-controller="studentsDayCtrl">
	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />


	<div class="container block-ui-main" block-ui="main">

		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>
						Estudiantes <small>Grupo 4°A : Matematicas </small>
						<button id="btn-save" class="btn btn-lg btn-danger" ng-click="">Guardar</button>
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

				<table st-table="studentsDisplayed" st-safe-src="students" class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th class="sort-header" st-sort="name">Nombre</th>
							<th class="sort-header" st-sort="lastName">Apellido</th>
						</tr>
					</thead>
					<tbody>
						<tr st-select-row="row" st-select-mode="single" ng-repeat="row in studentsDisplayed" ng-click="rowSelect(row)">
							<td><img class="media-object media-user-list media-user-medium" src="resources/img/nophoto.png"
								alt="..."></td>
							<td>{{row.name | capitalize}}</td>
							<td>{{row.lastName | capitalize}}</td>
							<!-- 							<td><a ng-href="mailto:{{student.email}}">email</a></td> -->
					</tbody>
				</table>

			</div>

			<div class="col-md-9">

				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">{{student.name | capitalize}} {{student.lastName | capitalize}}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png"
									class="img-circle img-responsive">
							</div>

<!-- 							<div class="col-xs-10 col-sm-10 hidden-md hidden-lg"> -->
<!-- 								<br> -->
<!-- 								<dl> -->
<!-- 									<dt>DEPARTMENT:</dt> -->
<!-- 									<dd>Administrator</dd> -->
<!-- 									<dt>HIRE DATE</dt> -->
<!-- 									<dd>11/12/2013</dd> -->
<!-- 									<dt>DATE OF BIRTH</dt> -->
<!-- 									<dd>11/12/2013</dd> -->
<!-- 									<dt>GENDER</dt> -->
<!-- 									<dd>Male</dd> -->
<!-- 								</dl> -->
<!-- 							</div> -->
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Grupo:</td>
											<td>Programming</td>
										</tr>
										<tr>
											<td>Hire date:</td>
											<td>06/23/2013</td>
										</tr>
										<tr>
											<td>Fecha de Nacimiento</td>
											<td>{{student.birthDate}}</td>
										</tr>

										<tr>
										<tr>
											<td>Genero</td>
											<td>{{student.gender | capitalize}}</td>
										</tr>
										<tr>
											<td>Home Address</td>
											<td>Kathmandu,Nepal</td>
										</tr>
										<tr>
											<td>Email</td>
											<td><a href="mailto:>{{student.email}}">{{student.email}}</a></td>
										</tr>
										<td>Phone Number</td>
										<td>123-4567-890(Landline)<br> <br>555-4567-890(Mobile)
										</td>

										</tr>

									</tbody>
								</table>

								<a href="#" class="btn btn-primary">My Sales Performance</a> <a href="#" class="btn btn-primary">Team
									Sales Performance</a>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip" type="button"
							class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a> <span class="pull-right">
							<a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button"
							class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a> <a
							data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i
								class="glyphicon glyphicon-remove"></i></a>
						</span>
					</div>

				</div>

			</div>

		</div>


	</div>
</body>





<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />

<script src="resources/app/controllers/studentsDayController.js"></script>
</html>
