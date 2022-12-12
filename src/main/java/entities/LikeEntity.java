package entities;

import lombok.Data;

import javax.persistence.*;
/*
класс-сущность лайк
 */
@Data
@Entity
@Table(name = "likes")
public class LikeEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
