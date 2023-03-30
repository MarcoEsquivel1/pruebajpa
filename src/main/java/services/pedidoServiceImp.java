package services;

import interfaces.Operations;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Pedido;
import java.util.List;

@Stateless
public class pedidoServiceImp implements Operations<Pedido> {
    @PersistenceContext(unitName = "default")
    EntityManager em;
    @Override
    public List<Pedido> listar() {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente ", Pedido.class).getResultList();
    }
    @Override
    public void insertar(Pedido pedido) {
        em.persist(pedido);
    }

    @Override
    public void eliminar(int id) {
        Pedido pedido = em.find(Pedido.class, id);
        em.remove(em.merge(pedido));
    }

    @Override
    public void actualizar(Pedido pedido) {
        em.merge(pedido);
    }

    @Override
    public Pedido buscar(int id) {
        return em.find(Pedido.class, id);
    }
}
