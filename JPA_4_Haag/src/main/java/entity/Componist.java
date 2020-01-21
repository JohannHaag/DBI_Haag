package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Componist")
public class Componist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "componist",cascade = CascadeType.ALL)
    List<Song> songs;

    public Componist(String name){
        this.name = name;
        songs = new ArrayList<>();
    }

    public Componist(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public  void  setName(String name){
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public  void  setSongs(List<Song> songs){
        this.songs = songs;
    }
}
