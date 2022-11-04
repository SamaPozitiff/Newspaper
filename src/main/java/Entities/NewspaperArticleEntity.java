package Entities;


import lombok.Data;


import javax.persistence.*;
import javax.swing.*;

import java.util.*;
import java.util.List;

@Data
@Entity
@Table(name = "articles")
public class NewspaperArticleEntity {
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
    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;
    @Column (name = "date")
    private Date publicationDate;
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private List<CommentEntity> comments;
    @Column(name= "likes")
    private int likes;

    public NewspaperArticleEntity(){

    }

    public NewspaperArticleEntity(Long id, String title, ImageIcon image, String description, UserEntity author) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.publicationDate = new Date();
        this.comments = new ArrayList<>();
        this.likes = 0;
    }

    public NewspaperArticleEntity(Long id, String title,ImageIcon image, String description, UserEntity author, Date date){
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.publicationDate = date;
        this.comments = new ArrayList<>();
        this.likes = 0;
    }

    public NewspaperArticleEntity(String title, ImageIcon image, String description, UserEntity author) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.publicationDate = new Date();
        this.comments = new ArrayList<>();
    }

    public void addComment(CommentEntity comment){
        comments.add(comment);
    }

}
