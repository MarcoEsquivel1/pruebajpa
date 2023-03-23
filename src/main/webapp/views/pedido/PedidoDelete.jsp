<%-- 
    Document   : PedidoDelete
    Created on : 03-01-2023, 01:48:52 PM
    Author     : Mery Acevedo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Eliminar pedido</h1>

<c:if test="${not empty pedido}">
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">¿Está seguro de eliminar el pedido?</h4>
        <p>Total: ${pedido.total}</p>
        <p>Estado: ${pedido.estado}</p>
        <p>Fecha: ${pedido.fecha}</p>

        <hr>
        <form action="${pageContext.request.contextPath}/pedidos/destroy" method="post">
            <input type="hidden" name="id" value="${pedido.id}">
            <button type="submit" class="btn btn-danger">Eliminar</button>
            <a href="${pageContext.request.contextPath}/pedidos" class="btn btn-primary">Cancelar</a>
        </form>
    </div>
</c:if>

<jsp:include page="/layout/footer.jsp"></jsp:include>
