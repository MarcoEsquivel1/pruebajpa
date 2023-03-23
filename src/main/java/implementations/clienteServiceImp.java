package implementations;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import models.Cliente;
import services.clienteService;
import interfaces.clienteDAO;
import java.util.List;

@Stateless
public class clienteServiceImp implements clienteService {
    @Inject
    private clienteDAO clienteDAO;
    @Override
    public List<Cliente> listar() {
        return clienteDAO.listar();
    }

    @Override
    public void insertar(Cliente cliente) {
        clienteDAO.insertar(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        clienteDAO.eliminar(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return clienteDAO.buscar(cliente);
    }
}
