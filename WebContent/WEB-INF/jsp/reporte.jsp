<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sucursales</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="./codebase/reporte.css"/>
<link type="text/css" rel="stylesheet" href="./codebase/dhtmlxscheduler2.css"/>
<link rel="stylesheet" type="text/css" href="./codebase/sweetalert.css">
</head>
<body>

	<!-- Encabezado -->
	<div class = "header" id = "header"></div>
	<!-- Fin Encabezado -->
	
	<!-- Menu -->
	<div class = "menu" id = "menu"><%@ include file="menu.jsp" %></div>
	<!-- Fin Menu -->
	
	<!-- Contenido -->
	<div class = "content" id = "content">
	
		<!-- Titulo de la pantalla -->
		<div class = "sample" id = "sample" >
			<div class = "name" id = "name">Sucursales</div>
			<div class = "dsc" id = "dsc"></div>
			<div class = "clear" id = "clear"></div>
		</div>
		<!-- Fin Titulo de la pantalla -->
	
		<!-- Contenido Principal -->
		<div class = "scheduler" id = "scheduler">
			<div class = "dhx_cal_container" id = "scheduler_her">
				<div class = "dhx_cal_navline" id = "dhx_cal_navline">
					<div class = "dhx_cal_date" id = "dhx_cal_date">
						<!--  <div class = "caja">
							<select id="cCliente" name="cCliente" onchange="carga_Sucursal();">
						  		<option selected="selected">Seleccione una opci�n:</option>
								<c:forEach items="${lista_ctCliente}" var="ctCliente">
									<option value="${ctCliente.cCliente}">${ctCliente.cRazonS}</option>
								</c:forEach>
							</select>
						</div>-->
					</div>
				</div>
				
				<!-- Tabla de sucursales -->
				<div id="table" class = "table">
					<table id="mytable" class = "mytable">
						<thead>
							<tr>
								<th onclick="TablaOrden.en('mytable',0)" class="text-left" width="20%">Sucursal<img style="display: block;margin-left: auto;margin-right: auto;" 
								src="codebase/imgs/arrow-down-sign-to-navigate.png"/></th>
								<th colspan="2" style="text-align: center" width="60%">Nombre</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				<!-- Fin Tabla de sucursales -->
			</div>
		</div>
		<!-- Fin Contenido Principal -->
	</div>
	<!-- Fin Contenido -->
	
	
	<!-- Scrips  -->
	<script type="text/javascript" src = "codebase/js/lib/jquery-3.1.0.js"></script>
	<script type="text/javascript" src = "codebase/js/lib/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript" src = "codebase/js/lib/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src = "codebase/js/reporte.js"></script>
	<script src="codebase/js/lib/sweetalert.min.js"></script>
	<script type="text/javascript" src = "codebase/js/lib/TablaOrden.js"></script>  
	<!-- Fin Scripts -->
</body>
</html>