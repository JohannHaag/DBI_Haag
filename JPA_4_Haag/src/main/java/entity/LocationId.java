package entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LocationId implements Serializable {

    private String street;
    private String place;
    public LocationId(){}

    public LocationId(String street, String place){
        this.street = street;
        this.place = place;
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