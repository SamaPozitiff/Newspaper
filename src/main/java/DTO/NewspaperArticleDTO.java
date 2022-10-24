package DTO;


import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

@Entity
@Table(name = "articles")
public class NewspaperArticleDTO{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private ImageIcon image;

    @Column(name = "description")
    private String description;

    //@ManyToOne
    //@JoinColumn(name = "id_author")//foreign key
    //private UserDTO author;

    @Column(name = "author")
    private long author = 1L;

    @Column (name = "date")
    private Date publicationDate;

    @Column(name = "comment_ref")//foreign_key
    List comments;

    @Column(name = "likes")
    int likes;

    public void setTitle(String title) {
        this.title = title;
    }

   // public void setAuthor(UserDTO author) {
   //     this.author = author;
  //  }

    public Long getId(){
        return id;
    }
}
