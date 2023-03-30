package controllers;

import interfaces.Operations;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cliente;
import models.Pedido;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "pedidos", urlPatterns = {"/pedidos", "/pedidos/create", "/pedidos/update", "/pedidos/delete", "/pedidos/edit", "/pedidos/save", "/pedidos/destroy"})
public class pedidos extends HttpServlet {
    @Inject
    Operations<Pedido> pedidoService;
    @Inject
    Operations<Cliente> clienteService;
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

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String view = "../views/pedido/PedidoForm.jsp";
        try {
            List<Cliente> clientes = clienteService.listar();
            request.setAttribute("clientes", clientes);
        } catch (Exception e) {
            String error = "No se pudo obtener la lista de clientes";
            request.setAttribute("error", error);
            request.getRequestDispatcher(view).forward(request, response);
        }
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoUpdate.jsp";
        searchPedido(request, response, view);
    }

    private void deletePedido(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoDelete.jsp";
        searchPedido(request, response, view);
    }

    private void searchPedido(HttpServletRequest request, HttpServletResponse response, String view) {
        int id = Integer.parseInt(request.getParameter("id"));
        Pedido pedido = null;
        try {
            pedido = pedidoService.buscar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("pedido", pedido);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendErrorToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String error = "Ha ocurrido un error inesperado";
        request.setAttribute("error", error);
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/pedidos/save":
                savePedido(request, response);
                break;
            case "/pedidos/update":
                updatePedido(request, response);
                break;
            case "/pedidos/destroy":
                destroyPedido(request, response);
                break;
            default:
                break;
        }
    }

    private void savePedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/pedido/PedidoForm.jsp";
        String error = "";
        try {
            // Data to save
            int clienteId = Integer.parseInt(request.getParameter("cliente"));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormateada = formato.parse(request.getParameter("fecha").toString());
            String total = request.getParameter("total");
            String estado = request.getParameter("estado");

            // Save data into model
            Pedido pedido = new Pedido();
            pedido.setCliente(clienteService.buscar(clienteId));
            pedido.setIdCliente(clienteId);
            pedido.setFecha(dateFormateada);
            pedido.setTotal(BigDecimal.valueOf(Double.parseDouble(total)));
            pedido.setEstado(estado);

            // Save data into database
            pedidoService.insertar(pedido);
        } catch (Exception e) {

                throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void updatePedido(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoList.jsp";
        Pedido pedido = null;
        try {
            // Data to update
            int id = Integer.parseInt(request.getParameter("id"));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormateada = formato.parse(request.getParameter("fecha").toString());
            String total = request.getParameter("total");
            String estado = request.getParameter("estado");

            // Update data into model
            pedido = pedidoService.buscar(id);
            pedido.setFecha(dateFormateada);
            pedido.setTotal(BigDecimal.valueOf(Double.parseDouble(total)));
            pedido.setEstado(estado);

            // Update data into database
            pedidoService.actualizar(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void destroyPedido(HttpServletRequest request, HttpServletResponse response) {
        String view = "../views/pedido/PedidoList.jsp";
        Pedido pedido = null;
        try {
            // Data to delete
            int id = Integer.parseInt(request.getParameter("id"));

            // Delete data into database
            pedidoService.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
