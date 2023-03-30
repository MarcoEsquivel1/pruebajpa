<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 03/22/23
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Cliente" %>
<%@ page import="java.util.List" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>

<h1 class="mb-3" style="text-align: center">Clientes</h1>
<a href="${pageContext.request.contextPath}/clientes/create" class="btn btn-success my-3 btn-sm">Agregar</a>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Direccion</th>
            <th scope="col">Telefono</th>
            <th scope="col">Email</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="cliente">
            <tr>
                <th scope="row"><c:out value="${cliente.id}"/></th>
                <td><c:out value="${cliente.nombre}"/></td>
                <td><c:out value="${cliente.direccion}"/></td>
                <td><c:out value="${cliente.telefono}"/></td>
                <td><c:out value="${cliente.email}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/clientes/edit?id=${cliente.id}" class="btn btn-primary">Editar</a>
                    <a href="${pageContext.request.contextPath}/clientes/delete?id=${cliente.id}" class="btn btn-danger">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="/layout/footer.jsp"></jsp:include>
