package control;

import entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class InitBean {

    @PersistenceContext
    EntityManager em;

    @Inject
    AlbumRepository albumRepository;

    @Inject
    PurchaseRepository purchaseRepository;

    @Inject
    SaleRepository saleRepository;

    @Inject
    SongRepository songRepository;

    @Inject
    WarehouseRepository warehouseRepository;

    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        initDb();
    }


    private void initDb() {
        Genre genre = new Genre("Rap");
        Genre genre1 = new Genre("Pop");
        Genre genre2 = new Genre("Hip-Pop");
        Artist artist = new Artist("Raf Camora");
        Artist artist1 = new Artist("Mark Forster");
        Artist artist2 = new Artist("Culcha Candela");
        Supplier supplier = new Supplier("DHL","Traun", "Dhlstraße 10");
        Supplier supplier1 = new Supplier("Lieferando","Wels","Lieferstraße 15");
        Supplier supplier2 = new Supplier("DPD","Linz","dpdstraße 20");
        Purchase purchase = new Purchase("Einkauf_1",supplier);
        Purchase purchase1 = new Purchase("Einkauf_2",supplier1);
        Purchase purchase2 = new Purchase("Einkauf_3",supplier2);
        Customer customer = new Customer("Harald","Linz","Glimpfingerstraße 2");
        Customer customer1 = new Customer("Dominic","Kematen","Pfeifferstraße 3");
        Customer customer2 = new Customer("Hans","Traun","Mitterfeldstraße 4");
        Sale sale = new Sale("Verkauf_1",customer);
        Sale sale1 = new Sale("Verkauf_2",customer1);
        Sale sale2 = new Sale("Verkauf_3",customer2);

        Componist componist = new Componist("Raf Camora");
        Componist componist1 = new Componist("Mark Forster");
        Componist componist2 = new Componist("Culcha Candela");
        LocationId locationId = new LocationId("Billrothstraße 20","Traun");
        LocationId locationId2 = new LocationId("Goethestraße 15","Linz");
        LocationId locationId3 = new LocationId("Maximilianstraße 25","Wels");
        Location location = new Location();
        Location location1 = new Location();
        Location location2 = new Location();
        location1.setId(locationId2);
        location2.setId(locationId3);
        location.setId(locationId);
        Shop shop = new Shop("Markt_1",location);
        Shop shop1 = new Shop("Markt_2",location1);
        Shop shop2 = new Shop("Markt_3",location2);
        Warehouse warehouse = new Warehouse(400.00);
        Warehouse warehouse1 = new Warehouse(500.00);
        Warehouse warehouse2 = new Warehouse(600.00);
        Album album = new Album("Bling Bling",Double.parseDouble("80.00"),purchase,sale,warehouse);
        Album album1 = new Album("Safari",Double.parseDouble("85.00"),purchase1,sale1,warehouse1);
        Album album2 = new Album("1220",Double.parseDouble("90.00"),purchase2,sale2,warehouse2);
        purchase.getAlbums().add(album);
        purchase1.getAlbums().add(album1);
        purchase2.getAlbums().add(album2);
        sale.getAlbums().add(album);
        sale1.getAlbums().add(album1);
        sale2.getAlbums().add(album2);
        warehouse.getAlbums().add(album);
        warehouse1.getAlbums().add(album1);
        warehouse2.getAlbums().add(album2);
        Song song = new Song("Hardcore high",180,album,genre,componist);
        Song song1 = new Song("Hochspannung",180,album1,genre1,componist1);
        Song song2 = new Song("Ok Cool",180,album2,genre2,componist2);
        album.getSongs().add(song);
        album1.getSongs().add(song1);
        album2.getSongs().add(song2);
        componist.getSongs().add(song);
        componist1.getSongs().add(song1);
        componist2.getSongs().add(song2);
        genre.getSongs().add(song);
        genre1.getSongs().add(song1);
        genre2.getSongs().add(song2);

        warehouseRepository.save(warehouse);
        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);
        em.persist(supplier);
        em.persist(supplier1);
        em.persist(supplier2);
        songRepository.save(song);
        songRepository.save(song1);
        songRepository.save(song2);
        saleRepository.save(sale);
        saleRepository.save(sale1);
        saleRepository.save(sale2);
        purchaseRepository.save(purchase);
        purchaseRepository.save(purchase1);
        purchaseRepository.save(purchase2);
        em.persist(customer);
        em.persist(customer1);
        em.persist(customer2);
        em.persist(componist);
        em.persist(componist1);
        em.persist(componist2);
        albumRepository.save(album);
        albumRepository.save(album1);
        albumRepository.save(album2);
        em.persist(genre);
        em.persist(genre1);
        em.persist(genre2);
        em.persist(artist);
        em.persist(artist1);
        em.persist(artist2);
        em.persist(location);
        em.persist(location1);
        em.persist(location2);
        em.persist(shop);
        em.persist(shop1);
        em.persist(shop2);
    }
}

