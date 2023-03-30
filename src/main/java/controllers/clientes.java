package controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import interfaces.Operations;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cliente;

import static java.lang.System.out;

@WebServlet(name = "clientes", urlPatterns = {"/clientes", "/clientes/create", "/clientes/update", "/clientes/delete", "/clientes/save", "/clientes/edit" , "/clientes/destroy"})
public class clientes extends HttpServlet {

    @Inject
    Operations<Cliente> clienteService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/clientes/create":
                showNewForm(request, response);
                break;
            case "/clientes/edit":
                showEditForm(request, response);
                break;
            case "/clientes/delete":
                deleteCliente(request, response);
                break;
            default:
                listClientes(request, response);
                break;
        }

    }

    private void listClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "views/cliente/ClienteList.jsp";
        List<Cliente> clientes = null;
        try {
            clientes = clienteService.listar();
        } catch (Exception e) {
            String error = "No se pudo obtener la lista de clientes";
            request.setAttribute("error", error);
            request.getRequestDispatcher(view).forward(request, response);
        }
        request.setAttribute("clientes", clientes);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/cliente/ClienteForm.jsp";
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/cliente/ClienteUpdate.jsp";
        searchCliente(request, response, view);
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/cliente/ClienteDelete.jsp";
        searchCliente(request, response, view);
    }

    private void searchCliente(HttpServletRequest request, HttpServletResponse response, String view) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = null;
        try {
            Cliente buscar = new Cliente();
            buscar.setId(id);
            cliente = (Cliente) clienteService.buscar(buscar);
        } catch (Exception e) {
            String error = "No se pudo obtener el cliente";
            request.setAttribute("error", error);
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException e1) {
                sendErrorToHome(request, response);
            } catch (IOException e1) {
                sendErrorToHome(request, response);
            }
        }
        request.setAttribute("cliente", cliente);
        try {
            request.getRequestDispatcher(view).forward(request, response);
        } catch (ServletException e) {
            sendErrorToHome(request, response);
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void sendErrorToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String error = "Ha ocurrido un error inesperado";
        request.setAttribute("error", error);
        response.sendRedirect(request.getContextPath() + "/");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/clientes/save":
                saveCliente(request, response);
                break;
            case "/clientes/update":
                updateCliente(request, response);
                break;
            case "/clientes/destroy":
                destroyCliente(request, response);
                break;
            default:
                listClientes(request, response);
                break;
        }
    }

    private void saveCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/cliente/ClienteList.jsp";
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEmail(request.getParameter("email"));
        try {
            clienteService.insertar(cliente);
        } catch (Exception e) {
            String error = "No se pudo guardar el cliente";
            request.setAttribute("error", error);
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException ex) {
                sendErrorToHome(request, response);
            } catch (IOException ex) {
                sendErrorToHome(request, response);
            }
        }
        try {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/cliente/ClienteList.jsp";
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setEmail(request.getParameter("email"));
        try {
            clienteService.actualizar(cliente);
        } catch (Exception e) {
            String error = "No se pudo actualizar el cliente";
            request.setAttribute("error", error);
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException ex) {
                sendErrorToHome(request, response);
            } catch (IOException ex) {
                sendErrorToHome(request, response);
            }
        }
        try {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    private void destroyCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String view = "../views/cliente/ClienteList.jsp";
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Cliente clienteBuscar = new Cliente();
            Cliente clienteEliminar = null;
            clienteBuscar.setId(id);
            clienteEliminar = (Cliente)clienteService.buscar(clienteBuscar);
            clienteService.eliminar(clienteEliminar);
        } catch (Exception e) {
            String error = "No se pudo eliminar el cliente";
            request.setAttribute("error", error);
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException ex) {
                sendErrorToHome(request, response);
            } catch (IOException ex) {
                sendErrorToHome(request, response);
            }
        }
        try {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (IOException e) {
            sendErrorToHome(request, response);
        }
    }

    public void destroy() {
    }
}