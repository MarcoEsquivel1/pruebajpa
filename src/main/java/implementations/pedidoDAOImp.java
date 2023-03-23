package implementations;

import interfaces.pedidoDAO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Pedido;

import java.util.List;

@Stateless
public class pedidoDAOImp implements pedidoDAO {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public void insertar(Pedido pedido) {em.persist(pedido);}

    @Override
    public void eliminar(Pedido pedido) {em.remove(em.merge(pedido));}

    @Override
    public void actualizar(Pedido pedido) {em.merge(pedido);}

    @Override
    public Pedido buscar(Pedido pedido) {
        return em.find(Pedido.class, pedido.getId());
    }

    @Override
    public List<Pedido> listar() {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente ", Pedido.class).getResultList();
    }

}
