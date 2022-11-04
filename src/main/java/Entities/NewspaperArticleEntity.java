package Entities;


import lombok.Data;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
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
    @OneToMany
    private List<CommentEntity> comments;
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
}
