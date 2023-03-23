<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 02/28/23
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Nuevo Cliente</h1>

<form action="${pageContext.request.contextPath}/clientes/save" method="post">
    <div class="form-group my-3">
        <label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" maxlength="50" minlength="3" required>
    </div>
    <div class="form-group my-3">
        <label for="direccion">Direccion</label>
        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Direccion" maxlength="100" minlength="3" required>
    </div>
    <div class="form-group my-3">
        <label for="telefono">Telefono</label>
        <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="Telefono" maxlength="9" minlength="9" required pattern="[0-9]{4}-[0-9]{4}">
    </div>
    <div class="form-group my-3">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Email" maxlength="50" minlength="3" required>
    </div>
    <div class="form-group my-3">
        <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
</form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
