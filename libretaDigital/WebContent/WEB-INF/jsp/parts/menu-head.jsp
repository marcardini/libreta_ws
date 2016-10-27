
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
				<li class="{{home}}"><a href="home.jsp">Home</a></li>
				<li class="dropdown {{datos}}"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Datos <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="{{datos1}}"><a href="fileUpload.jsp">Carga Masiva</a></li>
						<li><a href="#">Page 1-2</a></li>
						<li><a href="#">Page 1-3</a></li>
					</ul></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<!--         <li><a href="#"><span class="glyphicon glyphicon-user"></span>Registrarse</a></li> -->
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</div>
</nav>
