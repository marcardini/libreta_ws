<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%
	String codMenu = (String) request.getAttribute("codMenu");
	if (codMenu == null) {
		codMenu = "H0";
	}
	
	String logguedUserName = (String) request.getAttribute("logguedUserName");
	if (logguedUserName == null) {
		logguedUserName = "'Usuario desconocido'";
	}
%>

<script type="text/javascript">
	var codMenu = "<%=codMenu%>";
	var logguedUserName = <%=logguedUserName%>;
	
</script>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

<nav class=" navbar navbar-inverse navbar-fixed-top" ng-controller="menuCtrl">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Libreta Digital</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="{{home}}"><a href="index.jsp">Inicio</a></li>
				
				<li class="dropdown {{datos}}"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Gestión de datos<span	class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="{{datos1}}"><a href="fileUpload.jsp">Carga Masiva</a></li>
						<li class="{{datos2}}"><a href="data.jsp">Gestión de Datos</a></li>
					</ul>
				</li>					
					
				<li class="dropdown {{group}}"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Grupo<span class="caret"></span></a>
				<ul class="dropdown-menu">
						<li class="{{group1}}"><a href="assistControl.jsp">Control de asistencias</a></li>
						<li class="{{group2}}"><a href="studentsDay.jsp">Estudiantes</a></li>
						<li class="{{group3}}"><a href="planning.jsp">Planeamineto y Desarrollo del curso</a></li>						
				</ul></li>
				
				<li class="{{bulletins}}"><a href="bulletins.jsp">Boletines</a></li>
				
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
<!-- 				Docente Teresita Pérez -->{{logguedUserName}}
<%-- 				<%=logguedUserName.replaceAll("\"", "") %> --%>
				<span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li class=""><a href="">Mi Perfil</a></li>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
						<li><a href="javascript:formSubmit()">Salir</a></li>	
						</c:if>					
					</ul></li>
				
			</ul>
		</div>
	</div>
</nav>
