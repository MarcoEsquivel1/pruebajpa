package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cliente;
import models.Pedido;
import services.pedidoService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "pedidos", urlPatterns = {"/pedidos", "/pedidos/create", "/pedidos/update", "/pedidos/delete", "/pedidos/edit", "/pedidos/save", "/pedidos/destroy"})
public class pedidos extends HttpServlet {
    @Inject
    pedidoService pedidoService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/pedidos/create":
                showNewForm(request, response);
                break;
            case "/pedidos/edit":
                showEditForm(request, response);
                break;
            case "/pedidos/delete":
                deletePedido(request, response);
                break;
            default:
                listPedidos(request, response);
                break;
        }
    }

    private void listPedidos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String view = "views/pedido/PedidoList.jsp";
        List<Pedido> pedidos = null;
        try {
            pedidos = pedidoService.listar();
        } catch (Exception e) {
            String error = "No se pudo obtener la lista de pedidos";
            request.setAttribute("error", error);
            request.getRequestDispatcher(view).forward(request, response);
        }
        request.setAttribute("pedidos", pedidos);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/pedido/PedidoForm.jsp";
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deletePedido(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendErrorToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String error = "Ha ocurrido un error inesperado";
        request.setAttribute("error", error);
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
