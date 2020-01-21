package boundary;

import control.AlbumRepository;
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
import java.util.List;

@Path("album")
public class AlbumEndpoint {

    @PersistenceContext
    EntityManager em;

    @Inject
    AlbumRepository repository;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response returnResponse() {
        List<Album> albums = em
                .createNamedQuery("Album.findAll", Album.class)
                .getResultList();
        return Response
                .status(Response.Status.OK)
                .entity(albums)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(final @Context UriInfo uriInfo,
                           Album album) {
        Album a = new Album("Blingbl",Double.parseDouble("83"),em.find(Purchase.class,1L),em.find(Sale.class,2L),em.find(Warehouse.class,3L));
        a = em.merge(a);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/" + a.getId())
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
    public Response update(@PathParam("id") long id, Album album) {
        Song s = new Song("Alles Perfekt",Integer.parseInt("180"),em.find(Album.class,1L),em.find(Genre.class,2L),em.find(Componist.class,3L));
        album = repository.findById(id);
        album.setTitle("Bling");
        album.setUnitprice(Double.parseDouble("82"));
        album.getSongs().add(s);
        repository.save(album);
        return Response.ok().entity(album).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete ( @PathParam("id") long id){
        repository.delete(id);
        return Response.noContent().build();
    }
}
