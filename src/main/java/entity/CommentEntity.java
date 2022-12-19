package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/*
класс-сущность комментарий
 */
@Data
@Entity
@EqualsAndHashCode
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


}
