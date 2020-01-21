package boundary;

import control.PurchaseRepository;
import entity.*;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("purchase")
public class PurchaseEndpoint {

    @Inject
    PurchaseRepository repository;

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Purchase> findAll() {
        return em
                .createNamedQuery("Purchase.findAll", Purchase.class)
                .getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(final @Context UriInfo uriInfo,
                           Purchase purchase) {
        Purchase p = new Purchase("Einkauf_4",em.find(Supplier.class,1L));
                p = em.merge(p);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/" + p.getId())
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
    public Response update(@PathParam("id") long id, Purchase purchase) {
        Song s = new Song("Alles Perfekt",Integer.parseInt("180"),em.find(Album.class,1L),em.find(Genre.class,2L),em.find(Componist.class,3L));
        Album album = new Album("1221",Double.parseDouble("95"),em.find(Purchase.class,1L),em.find(Sale.class,2L),em.find(Warehouse.class,3L));
        album.getSongs().add(s);
            purchase = repository.findById(id);
            purchase.setName("Einkauf5");
            purchase.getAlbums().add(album);
            repository.save(purchase);
        return Response.ok().entity(purchase).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete ( @PathParam("id") long id){
            Purchase purchase = repository.findById(id);
            em.remove(purchase);
        return Response.noContent().build();
    }
}