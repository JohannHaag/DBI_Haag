package boundary;


import control.PurchaseRepository;
import control.SongRepository;
import entity.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("song")
public class SongEndpoint {

    @PersistenceContext
    EntityManager em;
    @Inject
    SongRepository repository;

    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Song> findAll() {
        return em
                .createNamedQuery("Song.findAll", Song.class)
                .getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(final @Context UriInfo uriInfo,Song song) {
        Song s = new Song("Alles Perfekt",Integer.parseInt("180"),em.find(Album.class,1L),em.find(Genre.class,3L),em.find(Componist.class,2L));
        s = em.merge(s);
            URI uri = uriInfo.getAbsolutePathBuilder()
                    .path("/" + s.getId())
                    .build();
            return Response
                    .created(uri)
                    .header("operation", "object created")
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") long id, Song song) {
        song = repository.findById(id);
        song.setName("Alles");
        song.setLength(Integer.parseInt("170"));
        repository.save(song);
        return Response.ok().entity(song).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete ( @PathParam("id") long id){
        Song song = repository.findById(id);
        em.remove(song);
        return Response.noContent().build();
    }
}
