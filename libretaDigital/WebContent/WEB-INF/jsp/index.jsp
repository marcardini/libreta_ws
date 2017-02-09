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
<link rel="stylesheet" href="bower_components/angular-bootstrap-calendar/dist/css/angular-bootstrap-calendar.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css" />
<link rel="stylesheet" href="resources/css/style.css">

</head>

<body ng-controller="calendarCtrl as vm">
	<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />
	<div class="container block-ui-main" block-ui="main">

		<div class="container calendar-container">
			<div>
				<div class="text-center">
					<div class="btn-group">
						<label class="btn btn-primary btn-lg btn-calendar" ng-model="vm.calendarView" uib-btn-radio="'year'">Año</label>
						<label class="btn btn-primary btn-lg btn-calendar" ng-model="vm.calendarView" uib-btn-radio="'month'">Mes</label>
						<!-- 					 <label	class="btn btn-primary" ng-model="vm.calendarView" uib-btn-radio="'week'">Semana</label>					   -->
					</div>
				</div>
				<br>
				<mwl-calendar events="vm.events" view="vm.calendarView" view-date="vm.viewDate"
					on-view-change-click="vm.viewChangeClicked(calendarNextView)"
					on-timespan-click="vm.click(calendarCell)"> </mwl-calendar>
			</div>
		</div>
	</div>
</body>

<script id="customMonthCell.html" type="text/ng-template">
  <div class="cal-month-day">
	<div class="row">
<div class="col-md-12">
		<span
      		class="pull-right"
      		data-cal-date
      		ng-click="vm.calendarCtrl.dateClicked(day.date)"
      		ng-bind="day.label">
    	</span>
</div>
</div>

	<div class="row">
<div class="col-md-12 div-btns-calandar">
   		<button type="button" class="btn btn-warning btn-md">Lista</button>
   		<button type="button" class="btn btn-success btn-md">Calificar</button>
</div>
</div>
	</div>
  </div>
</script>


<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />
<script src="resources/app/controllers/calendarController.js"></script>

</html>
