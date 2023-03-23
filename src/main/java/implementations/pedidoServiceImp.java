package implementations;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import models.Pedido;
import services.pedidoService;

import java.util.List;
import interfaces.pedidoDAO;

@Stateless
public class pedidoServiceImp implements pedidoService {
    @Inject
    private pedidoDAO pedidoDAO;
    @Override
    public void insertar(Pedido pedido) {
        pedidoDAO.insertar(pedido);
    }

    @Override
    public void eliminar(Pedido pedido) {
        pedidoDAO.eliminar(pedido);
    }

    @Override
    public void actualizar(Pedido pedido) {
        pedidoDAO.actualizar(pedido);
    }

    @Override
    public Pedido buscar(Pedido pedido) {
        return pedidoDAO.buscar(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return pedidoDAO.listar();
    }
}
