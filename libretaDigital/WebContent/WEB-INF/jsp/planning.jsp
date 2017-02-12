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
%>

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

	<div class="container block-ui-main" block-ui="main">

		<div class="page-header">
			<div class="row">
				<div class="col-md-8">
					<h1>
						Planeamiento<small></small>
					</h1>
				</div>
				<div class="col-md-4">

					<h1>
						<small class="date-small"> {{date | date:'dd-MM-yyyy'}}</small>

					</h1>
				</div>
			</div>
		</div>

		<div class="content-wrapper" ng-controller="planningCtrl">

			<uib-accordion close-others="oneAtATime">

			<div uib-accordion-group class="panel-primary" is-open="status.isProfessorsOpen" template-url="">
				<uib-accordion-heading>PROFESORES<i class="pull-right glyphicon"
					ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
				</uib-accordion-heading>


				<div class="panel-body" ng-controller="groupCtrl">
					<div class="row">

						<div class="col-md-12">

							<form id="idform" role="form" novalidate="" name="groupForm" class="text-left">
								<div class="form-group">
									<label for="comment">Comment:</label>
									<textarea class="form-control" rows="10" id="comment"></textarea>
								</div>
							</form>
							
						</div>
					</div>
					<!-- FIN ROW  -->
				</div>
				<!-- FIN PANEL-BODY  -->


			</div>

			<!-- 				<div uib-accordion-group class="panel-primary" is-open="status.isStudentsOpen" template-url=""> -->
			<!-- 					<uib-accordion-heading>ESTUDIANTES<i class="pull-right glyphicon" --> <!-- 						ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i> -->
			<!-- 					</uib-accordion-heading> --> <%-- 					<jsp:include page="/WEB-INF/jsp/parts/dataStudent.jsp" /> --%>
			<!-- 				</div> --> <!-- 				<div uib-accordion-group class="panel-primary" is-open="status.isGroupsOpen" template-url=""> -->
			<!-- 					<uib-accordion-heading>GRUPOS<i class="pull-right glyphicon" --> <!-- 						ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i> -->
			<!-- 					</uib-accordion-heading> --> <%-- 					<jsp:include page="/WEB-INF/jsp/parts/dataGroup.jsp" /> --%>
			<!-- 				</div> -->
			
			 </uib-accordion>

		</div>


	</div>
</body>




<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
<script src="resources/app/controllers/planningController.js"></script>

</html>
