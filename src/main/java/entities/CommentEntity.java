package entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;
    @Column(name = "description")
    @Size(max = 1000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity user;
    @Column(name = "date")
    private Date publicationDate;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    public CommentEntity(){

    }


    public CommentEntity(String description, UserEntity user, ArticleEntity article) {
        this.description = description;
        this.user = user;
        this.publicationDate = new Date();
        this.article = article;
    }

    public CommentEntity(String description, UserEntity user, Date publicationDate, ArticleEntity article) {
        this.description = description;
        this.user = user;
        this.publicationDate = publicationDate;
        this.article = article;
    }
}
