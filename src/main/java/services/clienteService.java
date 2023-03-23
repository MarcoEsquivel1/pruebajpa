package services;

import models.Cliente;

import java.util.List;

public interface clienteService {
    public List<Cliente> listar();
    public void insertar(Cliente cliente);
    public void eliminar(Cliente cliente);
    public void actualizar(Cliente cliente);
    public Cliente buscar(Cliente cliente);
}
