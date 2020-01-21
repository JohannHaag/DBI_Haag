package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Location")
public class Location implements Serializable {

    @EmbeddedId
    private LocationId id = new LocationId();

    public Location(){}

    public LocationId getId(){
        return id;
    }

    public void  setId(LocationId id){
        this.id = id;
    }

}
