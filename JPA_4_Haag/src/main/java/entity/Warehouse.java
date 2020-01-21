package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Warehouse")
@NamedQueries({
        @NamedQuery(name = "Warehouse.findAll",
                query = "select c FROM Warehouse c"),
        @NamedQuery(name = "Warehouse.findById",
                query = "select c FROM Warehouse c Where c.id = :id")
})
public class Warehouse  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity;


    @OneToMany(mappedBy = "warehouse",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Album> albums;

    public Warehouse(double quantity){
        albums = new ArrayList<>();
        this.quantity = quantity;
    }
    public Warehouse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbum(List<Album> albums) {
        this.albums = albums;
    }

}
