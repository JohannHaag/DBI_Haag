package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Song")
@NamedQueries({
        @NamedQuery(name = "Song.findAll",
        query = "select c FROM Song  c"),
        @NamedQuery(name = "Song.findById",
                query = "select c FROM Song  c Where c.id = :id")
})
public class Song implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int length;

    @ManyToOne
    Genre genre;

    @ManyToOne
    Album album;

    @ManyToOne
    Componist componist;

    public Song(String name, int length, Album album, Genre genre,Componist componist){
        this.name = name;
        this.length = length;
        this.album = album;
        this.genre = genre;
        this.componist = componist;
    }

    public Song(){}

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }
}
