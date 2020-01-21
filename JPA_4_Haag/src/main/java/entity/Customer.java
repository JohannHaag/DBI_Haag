package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")@NamedQueries({
        @NamedQuery(name = "Customer.findAll",
                query = "select c FROM Customer c"),
        @NamedQuery(name = "Customer.findById",
                query = "select c FROM Customer c Where c.id = :id")
})

public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String street;

    private String place;

    public Customer(String name,String place, String street){
        this.name = name;
        this.place = place;
        this.street = street;
    }

    public Customer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace(){
        return place;
    }
    public  void  setPlace(String place){
        this.place = place;
    }

    public String getName(){
        return name;
    }
    public  void  setName(String name){
        this.name = name;
    }
    public String getStreet(){
        return street;
    }
    public  void  setStreet(String street){
        this.street = street;
    }

}
