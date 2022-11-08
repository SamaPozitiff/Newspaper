package Entities;

import lombok.Data;

import javax.persistence.*;

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

    public LikeEntity() {
    }

    public LikeEntity(ArticleEntity article, UserEntity user) {
        this.article = article;
        this.user = user;
    }
}
