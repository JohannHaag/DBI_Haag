package boundary;

import control.SongRepository;
import control.WarehouseRepository;
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

@Path("warehouse")
public class WarehouseEndpoint {

    @PersistenceContext
    EntityManager em;
    @Inject
    WarehouseRepository repository;

    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Warehouse> findAll() {
        return em
                .createNamedQuery("Warehouse.findAll", Warehouse.class)
                .getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(final @Context UriInfo uriInfo, Warehouse warehouse) {
        Warehouse w = new Warehouse(Double.parseDouble("700"));
        w = em.merge(w);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/" + w.getId())
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
    public Response update(@PathParam("id") long id, Warehouse warehouse) {
        Song s = new Song("Alles Perfekt",Integer.parseInt("180"),em.find(Album.class,1L),em.find(Genre.class,2L),em.find(Componist.class,3L));
        Album album = new Album("1221",Double.parseDouble("95"),em.find(Purchase.class,1L),em.find(Sale.class,2L),em.find(Warehouse.class,3L));
        album.getSongs().add(s);
        warehouse = repository.findById(id);
        warehouse.setQuantity(Double.parseDouble("800"));
        warehouse.getAlbums().add(album);
        repository.save(warehouse);
        return Response.ok().entity(warehouse).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete ( @PathParam("id") long id){
        Warehouse warehouse = repository.findById(id);
        em.remove(warehouse);
        return Response.noContent().build();
    }
}
