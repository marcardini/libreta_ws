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

	String professors = (String) request.getAttribute("professors");
	if (professors == null) {
		professors = "[]";
	}

	String students = (String) request.getAttribute("students");
	if (students == null) {
		students = "[]";
	}

	String groups = (String) request.getAttribute("groups");
	if (groups == null) {
		groups = "[]";
	}
%>


<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bower_components/dropzone/dist/basic.css">
<link rel="stylesheet" href="bower_components/dropzone/dist/dropzone.css">
<link rel="stylesheet" href="bower_components/ng-notify/dist/ng-notify.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css"/>
<link rel="stylesheet" href="resources/css/style.css">


<script type="text/javascript">
	var professors =
<%=professors%>
	var students =
<%=students%>
	;
	var groups =
<%=groups%>
	;
</script>

</head>

<body>
	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

	<div class="container block-ui-main" block-ui="main">

		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>
						Gestión de datos
					</h1>
				</div>
				<div class="col-md-4">
					<h1>
						<small class="date-small"> {{date | date:'dd-MM-yyyy'}}</small>

					</h1>
				</div>
			</div>
		</div>

		<div class="content-wrapper" ng-controller="dataCtrl">

			<uib-accordion close-others="oneAtATime">
			
				<div uib-accordion-group class="panel-primary" is-open="status.isProfessorsOpen" template-url="">
					<uib-accordion-heading>PROFESORES<i class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
					</uib-accordion-heading>
					<jsp:include page="/WEB-INF/jsp/parts/dataProfessor.jsp" />
				</div>

				<div uib-accordion-group class="panel-primary" is-open="status.isStudentsOpen" template-url="">
					<uib-accordion-heading>ESTUDIANTES<i class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
					</uib-accordion-heading>
					<jsp:include page="/WEB-INF/jsp/parts/dataStudent.jsp" />
				</div>

				<div uib-accordion-group class="panel-primary" is-open="status.isGroupsOpen" template-url="">
					<uib-accordion-heading>GRUPOS<i class="pull-right glyphicon"
						ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
					</uib-accordion-heading>
					<jsp:include page="/WEB-INF/jsp/parts/dataGroup.jsp" />
				</div>

			</uib-accordion>

		</div>
	</div>

</body>

<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
<script src="resources/app/controllers/fileUploadController.js"></script>
<script src="resources/app/controllers/dataController.js"></script>
<script src="resources/app/controllers/professorController.js"></script>
<script src="resources/app/controllers/studentController.js"></script>
<script src="resources/app/controllers/groupController.js"></script>

<script type="text/javascript">
	Dropzone.autoDiscover = false;
</script>
</html>