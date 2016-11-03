
<%
	String codMenu = (String) request.getAttribute("codMenu");
	if (codMenu == null) {
		codMenu = "H0";
	}
%>

<script type="text/javascript">
var codMenu = "<%=codMenu%>";
</script>

<nav class="navbar navbar-inverse" ng-controller="menuCtrl">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Libreta Digital</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="{{home}}"><a href="#">Inicio</a></li>
				<li class="dropdown {{datos}}"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Gestión de datos<span
						class="caret"></span></a>

					<ul class="dropdown-menu">
						<li class="{{datos1}}"><a href="fileUpload.jsp">Carga Masiva</a></li>
						<li><a href="#">Docentes</a></li>
						<li><a href="#">Alumnos</a></li>
						<li><a href="#">Grupos</a></li>
						<li><a href="#">Programa</a></li>
					</ul></li>
				<li><a href="#">Control de asistencias</a></li>
<!-- 				<li><button ng-click = "test()">Test</button></li> -->
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<!--         <li><a href="#"><span class="glyphicon glyphicon-user"></span>Registrarse</a></li> -->
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</div>
</nav>
