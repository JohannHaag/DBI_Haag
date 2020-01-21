package boundary;
import control.PurchaseRepository;
import control.SaleRepository;
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

@Path("sale")
public class SaleEndpoint {

    @PersistenceContext
    EntityManager em;

    @Inject
    SaleRepository repository;


    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Sale> findAll() {
        return em
                .createNamedQuery("Sale.findAll", Sale.class)
                .getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(final @Context UriInfo uriInfo,
                           Sale sale) {
        Sale s = new Sale("Verkauf_4",em.find(Customer.class,2L));
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
    public Response update(@PathParam("id") long id, Sale sale) {
        Song s = new Song("Alles Perfekt",Integer.parseInt("180"),em.find(Album.class,1L),em.find(Genre.class,2L),em.find(Componist.class,3L));
        Album album = new Album("1221",Double.parseDouble("95"),em.find(Purchase.class,1L),em.find(Sale.class,2L),em.find(Warehouse.class,3L));
        album.getSongs().add(s);
        sale = repository.findById(id);
        sale.setName("Verkauf_5");
        sale.getAlbums().add(album);
        repository.save(sale);
        return Response.ok().entity(sale).build();
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
