package control;

import entity.Song;
import entity.Warehouse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class WarehouseRepository {
    @PersistenceContext
    EntityManager em;

    public List<Warehouse> findAll() {

        return em.createNamedQuery("Warehouse.findAll",Warehouse.class).getResultList();
    }

    public Warehouse save(Warehouse warehouse) {

        em.persist(warehouse);
        return warehouse;
    }

    public Warehouse findById( long id) {
        return em.createNamedQuery("Warehouse.findById", Warehouse.class).setParameter("id", id).getSingleResult();
    }

    public void delete(long id) {
        em.remove(id);
    }
}
