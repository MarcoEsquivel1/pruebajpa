<%-- 
    Document   : PedidoUpdate
    Created on : 03-01-2023, 01:48:35 PM
    Author     : Mery Acevedo
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Actualizar pedido</h1>

<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
            ${error}
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/pedidos/update" method="post">
    <input type="hidden" name="id" value="${pedido.id}">
    <div class="form-group my-3">
        <label for="total">Total</label>
        <input type="number" step="0.01" class="form-control" id="total" name="total" value="${pedido.total}"  required>
    </div>
    <div class="form-group my-3">
        <label for="estado">Estado</label>
        <select class="form-select" name="estado" id="estado" required>
            <option value="${pedido.estado}" selected hidden="hidden">${pedido.estado}</option>
            <option value="Pendiente">Pendiente</option>
            <option value="Enviado">Enviado</option>
            <option value="Entregado">Entregado</option>
        </select>
    </div>
    <div class="form-group my-3">
        <label for="fecha">Fecha</label>
        <input type="date" class="form-control" id="fecha" name="fecha" value="${pedido.fecha}" >
    </div>
     <div class="form-group my-3">
        <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
</form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
