<%-- 
    Document   : PedidoForm
    Created on : 03-01-2023, 01:48:09 PM
    Author     : Mery Acevedo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Nuevo pedido</h1>

<form action="${pageContext.request.contextPath}/pedidos/save" method="post">
        <div class="form-group my-3">
            <label for="cliente">Cliente</label>
            <select class="form-select" name="cliente" id="cliente" required>
                <c:forEach items="${clientes}" var="cliente">
                    <option value="${cliente.id}">${cliente.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group my-3">
            <label for="total">Total</label>
            <input type="number" step="0.01" class="form-control" id="total" min="0" lang="en" name="total" required>
        </div>
        <div class="form-group my-3">
            <label for="estado">Estado</label>
            <select class="form-select" name="estado" id="estado" required>
                <option value="Pendiente">Pendiente</option>
                <option value="Enviado">Enviado</option>
                <option value="Entregado">Entregado</option>
            </select>
        </div>
        <div class="form-group my-3">
            <label for="fecha">Fecha</label>
            <input type="date" class="form-control" id="fecha" name="fecha" placeholder="06/01/2023"  required>
        </div>
    <div class="form-group my-3">
        <button type="submit" class="btn btn-primary" >Guardar</button>
    </div>
  </form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
