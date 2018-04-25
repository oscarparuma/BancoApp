<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Clientes</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Crear Cliente
</h1>

<c:url var="addAction" value="/cliente/add" ></c:url>

<form:form action="${addAction}" commandName="cliente">
<table>
	<c:if test="${!empty cliente.nombre}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="nombre">
				<spring:message text="Nombre"/>
			</form:label>
		</td>
		<td>
			<form:input path="nombre" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="direccion">
				<spring:message text="Direcci&oacute;n"/>
			</form:label>
		</td>
		<td>
			<form:input path="direccion" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty cliente.nombre}">
				<input type="submit"
					value="<spring:message text="Editar Cliente"/>" />
			</c:if>
			<c:if test="${empty cliente.nombre}">
				<input type="submit"
					value="<spring:message text="Crear Cliente"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Listado de Clientes</h3>
<c:if test="${!empty listClientes}">
	<table class="tg">
	<tr>
		<th width="80">ID</th>
		<th width="120">Nombre</th>
		<th width="120">Direcci&oacute;n</th>
		<th width="60">Editar</th>
		<th width="60">Borrar</th>
	</tr>
	<c:forEach items="${listClientes}" var="cliente">
		<tr>
			<td>${cliente.id}</td>
			<td>${cliente.nombre}</td>
			<td>${cliente.direccion}</td>
			<td><a href="<c:url value='/edit/${cliente.id}' />" >Editar</a></td>
			<td><a href="<c:url value='/remove/${cliente.id}' />" >Borrar</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>