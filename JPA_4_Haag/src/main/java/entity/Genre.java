package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Genre")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "genre",cascade = CascadeType.ALL)
    List<Song> songs;

    private String name;

    public Genre(String name){
        this.name = name;
        songs = new ArrayList<>();
    }

    public Genre(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Song> getSongs(){
        return songs;
    }
    public  void  setSongs(List<Song> songs){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public  void  setName(String name){
        this.name = name;
    }
}
