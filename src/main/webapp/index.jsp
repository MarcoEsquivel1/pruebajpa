<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1>Inicio</h1>

<h3 class="display-3 my-4">Prueba JPA</h3>
<h3 class="display-3 my-4">Alumno</h3>
<ul>
    <li>Marco René Esquivel Juárez</li>
</ul>

<c:if test="${error != null}">
    <div class="alert alert-danger" role="alert">
            ${error}
    </div>
</c:if>

<jsp:include page="/layout/footer.jsp"></jsp:include>
