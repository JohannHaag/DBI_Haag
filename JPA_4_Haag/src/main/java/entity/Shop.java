package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Shop")
public class Shop implements Serializable
{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    Location location;



    private String name;

    public  Shop(){}

    public Shop(String name,Location location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
