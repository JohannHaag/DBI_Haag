package control;


import entity.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import java.util.List;

@Transactional
public class SaleRepository {

    @PersistenceContext
    EntityManager em;


    public List<Sale> findAll() {

        return em.createNamedQuery("Sale.findAll",Sale.class).getResultList();
    }

    public Sale save(Sale sale) {

        em.persist(sale);
        return sale;
    }

    public Sale findById( long id) {

        return em.createNamedQuery("Sale.findById", Sale.class).setParameter("id",id).getSingleResult();
    }

    public void delete(long id) {
        em.remove(findById(id));
    }


}
