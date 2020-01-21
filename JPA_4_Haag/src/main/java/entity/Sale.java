package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Sale")
@NamedQueries({
        @NamedQuery(name = "Sale.findAll",
                query = "select c FROM Sale  c"),
        @NamedQuery(name = "Sale.findById",
                query = "select c FROM Sale  c Where c.id = :id")
})
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Album> albums;


    public Sale(){}

    public Sale(String name, Customer customer){
        albums = new ArrayList<>();
        this.name = name;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    public Customer getCustomer() {
        return customer;

    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
