package services;

import models.Pedido;
import java.util.List;

public interface pedidoService {
    public void insertar(Pedido pedido);
    public void eliminar(Pedido pedido);
    public void actualizar(Pedido pedido);
    public Pedido buscar(Pedido pedido);
    public List<Pedido> listar();
}
