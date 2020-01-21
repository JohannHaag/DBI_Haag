package control;


import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AlbumRepository {

    @PersistenceContext
    EntityManager em;

    public List<Album> findAll() {

        return em.createNamedQuery("Album.findAll",Album.class).getResultList();
    }

    public Album save(Album album) {

        em.persist(album);
        return album;
    }

    public Album findById( long id) {

        return em.createNamedQuery("Album.findById", Album.class).setParameter("id",id).getSingleResult();
    }

    public void delete(long id) {
        em.remove(id);
    }
}
