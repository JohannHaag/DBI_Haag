package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String street;

    private String place;

    public Supplier(String name,String place, String street){
        this.name = name;
        this.place = place;
        this.street = street;
    }

    public Supplier(){}

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

    public String getStreet(){
        return street;
    }
    public  void  setStreet(String street){
        this.street = street;
    }
}

