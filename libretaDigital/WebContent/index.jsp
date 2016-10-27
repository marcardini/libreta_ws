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

<script type="text/javascript">
var pageTitle = <%=pageTitle%>;
</script>

<title><%=pageTitle%></title>
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">

</head>

<body>

	<div class="container">
		<jsp:include page="/WEB-INF/jsp/parts/menu-head.jsp" />

		<br>
		<div style="text-align: center">
			<h2>
				Hey You..!! This is your 1st Spring MCV Tutorial..<br> <br>
			</h2>
			<h3>
				<a href="welcome.jsp">Click here to See Welcome Message... </a>(to check Spring MVC Controller... @RequestMapping("/welcome"))
			</h3>
		</div>

	</div>
</body>


<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />


</html>