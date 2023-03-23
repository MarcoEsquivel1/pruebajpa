package interfaces;

import models.Cliente;

import java.util.List;

public interface clienteDAO {
    public void insertar(Cliente cliente);
    public void eliminar(Cliente cliente);
    public void actualizar(Cliente cliente);
    public Cliente buscar(Cliente id);
    public List<Cliente> listar();
}
