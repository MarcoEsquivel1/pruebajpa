<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 03/01/23
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Eliminar Cliente</h1>

<c:if test="${not empty cliente}">
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">¿Está seguro de eliminar el cliente y todos sus pedidos?</h4>
        <p>Nombre: ${cliente.nombre}</p>
        <p>Dirección: ${cliente.direccion}</p>
        <p>Telefono: ${cliente.telefono}</p>
        <p>Correo: ${cliente.email}</p>
        <hr>
        <form action="${pageContext.request.contextPath}/clientes/destroy" method="post">
            <input type="hidden" name="id" value="${cliente.id}">
            <button type="submit" class="btn btn-danger">Eliminar</button>
            <a href="${pageContext.request.contextPath}/clientes" class="btn btn-primary">Cancelar</a>
        </form>
    </div>
</c:if>

<jsp:include page="/layout/footer.jsp"></jsp:include>
