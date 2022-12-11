package entities;


import lombok.Data;


import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.Size;

import java.util.*;
import java.util.List;
/*
класс-сущность статья
 */
@Data
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    @Size (max = 255)
    private String title;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;
    @Column (name = "date")
    private Date publicationDate;

}
