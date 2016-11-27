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
var students = <%=students%>
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

				<table st-table="students" class="table table-striped">
					<thead>
						<tr>
							<th st-sort="firstName">first name</th>
							<th st-sort="lastName">last name</th>							
							<th>email</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="student in students">
							<td>{{student.name | uppercase}}</td>
							<td>{{student.lastName}}</td>							
							<td><a ng-href="mailto:{{student.email}}">email</a></td>
					</tbody>
				</table>

			</div>
		</div>


	</div>
</body>





<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />

<script src="resources/app/controllers/studentsDayController.js"></script>
</html>
