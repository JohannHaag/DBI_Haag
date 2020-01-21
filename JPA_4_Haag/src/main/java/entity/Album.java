package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Album")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select c FROM Album c"),
        @NamedQuery(name = "Album.findById",
                query = "select c FROM Album c Where c.id = :id")
})
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double unitprice;


    @ManyToOne
    Purchase purchase;

    @ManyToOne
    Sale sale;

    @ManyToOne
    Warehouse warehouse;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Song> songs;


    public Album(String title, double unitprice, Purchase purchase, Sale sale, Warehouse warehouse){

        songs = new ArrayList<>();
        this.title = title;
        this.unitprice = unitprice;
        this.purchase = purchase;
        this.sale = sale;
        this.warehouse = warehouse;
    }

    public Album(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Song> getSongs(){
        return songs;
    }

    public void setSongs(List<Song> songs){
        this.songs = songs;
    }

    public String getTitle(){
        return title;
    }
    public  void  setTitle(String title){
        this.title = title;
    }

    public Double getUnitPrice(){
        return unitprice;
    }
    public  void  setUnitprice(Double unitprice){
        this.unitprice = unitprice;
    }

}
