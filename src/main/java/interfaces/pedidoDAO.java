package interfaces;

import models.Pedido;

import java.util.List;

public interface pedidoDAO {
    public void insertar(Pedido pedido);
    public void eliminar(Pedido pedido);
    public void actualizar(Pedido pedido);
    public Pedido buscar(Pedido id);
    public List<Pedido> listar();
}
