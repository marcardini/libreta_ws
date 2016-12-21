<%@ page import="org.codehaus.jackson.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet"
	href="bower_components/angular-bootstrap-calendar/dist/css/angular-bootstrap-calendar.min.css">
<link rel="stylesheet" href="bower_components/angular-block-ui/dist/angular-block-ui.min.css" />
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/login.css">
</head>


<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Sign in</h1>
				<div class="account-wall">
					<img class="profile-img" src="resources/img/nophoto.png"	alt="">
					
					<form class="form-signin" action="<c:url value='j_spring_security_check' />" method="post">
<%-- 						<form class="form-signin" action="<c:url value='/login' />" method="post"> --%>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											
						<input type="text" class="form-control" placeholder="Email" name="username" required autofocus> 
						<input type="password" class="form-control" placeholder="Password" name="password" required> 
							
						<button class="btn btn-lg btn-primary btn-block" type="submit" value="Login">Sign in</button>


						<label class="checkbox pull-left"> <input type="checkbox" value="remember-me"> Remember me
						</label> <a href="#" class="pull-right need-help">Need help? </a> <span class="clearfix"></span>
					</form>
				</div>
				<!-- 				<a href="#" class="text-center new-account">Create an account </a> -->
			</div>
		</div>
	</div>



	<%-- 	<c:url var="loginUrl" value="/login" /> --%>
	<%-- 	<form action="${loginUrl}" method="post" class="form-horizontal"> --%>
	<%-- 		<c:if test="${param.error != null}"> --%>
	<!-- 			<div class="alert alert-danger"> -->
	<!-- 				<p>Invalid username and password.</p> -->
	<!-- 			</div> -->
	<%-- 		</c:if> --%>
	<%-- 		<c:if test="${param.logout != null}"> --%>
	<!-- 			<div class="alert alert-success"> -->
	<!-- 				<p>You have been logged out successfully.</p> -->
	<!-- 			</div> -->
	<%-- 		</c:if> --%>
	<!-- 		<div class="input-group input-sm"> -->
	<!-- 			<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label> <input type="text" -->
	<!-- 				class="form-control" id="username" name="ssoId" placeholder="Enter Username" required> -->
	<!-- 		</div> -->
	<!-- 		<div class="input-group input-sm"> -->
	<!-- 			<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> <input type="password" -->
	<!-- 				class="form-control" id="password" name="password" placeholder="Enter Password" required> -->
	<!-- 		</div> -->
	<%-- 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>

	<!-- 		<div class="form-actions"> -->
	<!-- 			<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in"> -->
	<!-- 		</div> -->
	<!-- 	</form> -->



</body>




<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp" />


</html>
