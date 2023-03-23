package com.example.pruebajpa;

import java.io.*;
import java.util.List;

import services.clienteService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cliente;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Inject
    clienteService clienteService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cliente> clientes = clienteService.listar();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("views/clientes.jsp").forward(request, response);


    }

    public void destroy() {
    }
}