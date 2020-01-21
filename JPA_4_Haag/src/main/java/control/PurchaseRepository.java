package control;


import entity.Purchase;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import java.util.List;

@Transactional
public class PurchaseRepository {

    @PersistenceContext
    EntityManager em;


    public List<Purchase> findAll() {

        return em.createNamedQuery("Purchase.findAll",Purchase.class).getResultList();
    }

    public Purchase save(Purchase purchase) {

        em.persist(purchase);
        return purchase;
    }

    public Purchase findById( long id) {
        return em.createNamedQuery("Purchase.findById", Purchase.class).setParameter("id", id).getSingleResult();
    }

    public void delete(long id) {
       em.remove(id);
    }

}
