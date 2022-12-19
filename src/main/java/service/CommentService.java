package service;

import entity.ArticleEntity;
import entity.CommentEntity;
import entity.UserEntity;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public interface CommentService {

    void save(CommentEntity comment);

    List<CommentEntity> findPagedCommentsOfArticle(Integer page, Long articleId);

    CommentEntity findById(Long id);

    void delete(Long commentId);

    List<CommentEntity> findAllCommentsOfArticle(Long articleId);

    CommentEntity newComment(@NonNull String description, @NonNull UserEntity user, @NonNull ArticleEntity article);

    CommentEntity newCommentWithDate(@NonNull String description, @NonNull UserEntity user,
                                     @NonNull ArticleEntity article, @NonNull Date publicationDate);
}
