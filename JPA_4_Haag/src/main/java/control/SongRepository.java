package control;

import entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SongRepository {
    @PersistenceContext
    EntityManager em;

    public List<Song> findAll() {

        return em.createNamedQuery("Song.findAll",Song.class).getResultList();
    }

    public Song save(Song song) {

        em.persist(song);
        return song;
    }

    public Song findById( long id) {
        return em.createNamedQuery("Song.findById", Song.class).setParameter("id", id).getSingleResult();
    }

    public void delete(long id) {
        em.remove(findById(id));
    }
}
