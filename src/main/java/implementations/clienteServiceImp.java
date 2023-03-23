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
}
