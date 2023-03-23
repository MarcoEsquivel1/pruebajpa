package implementations;

import interfaces.clienteDAO;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.Cliente;

import java.util.List;

@Stateless
public class clienteDAOImp implements clienteDAO {
    @PersistenceContext(unitName = "default")
    EntityManager em;
    @Override
    public void insertar(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        em.remove(em.merge(cliente));
    }

    @Override
    public void actualizar(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public Cliente buscar(Cliente cliente) {
       return em.find(Cliente.class, cliente.getId());
    }

    @Override
    public List<Cliente> listar() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
}
