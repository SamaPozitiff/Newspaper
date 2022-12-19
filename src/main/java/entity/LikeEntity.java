package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
/*
класс-сущность лайк
 */
@Data
@Entity
@EqualsAndHashCode
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
