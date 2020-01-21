package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Purchase")
@NamedQueries({
        @NamedQuery(name = "Purchase.findAll",
                query = "select c FROM Purchase  c"),
        @NamedQuery(name = "Purchase.findById",
                query = "select c FROM Purchase c Where c.id = :id")
})
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    Supplier supplier;


    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Album> albums;

    public Purchase(){}

    public Purchase(String name ,Supplier supplier){
        albums = new ArrayList<>();
        this.name = name;
        this.supplier = supplier;
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

    public Supplier getSupplier() {
        return supplier;

    }
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
}
