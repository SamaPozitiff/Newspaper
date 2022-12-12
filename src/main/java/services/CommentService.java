package services;

import entities.ArticleEntity;
import entities.CommentEntity;
import entities.UserEntity;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final int COMMENTS_ON_PAGE = 3;
    private CommentRepository repository;

    public CommentService(CommentRepository repository){
        this.repository = repository;
    }
    /**
    сохранение комментария
     */
    public void save(CommentEntity comment){
        repository.save(comment);
    }
    /*
    найти все комментарии к конкретной статье постранично
     */
    public List<CommentEntity> findPagedCommentsOfArticle(Integer page, Long articleId){
        return  repository.getCommentsOfArticle(articleId).stream().skip((long) page * COMMENTS_ON_PAGE).limit(COMMENTS_ON_PAGE).collect(Collectors.toList());
    }

    public CommentEntity findById(Long id){
        try {
            return repository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }
    /*
    удаление комментария
     */
    public void delete(Long commentId){
        repository.deleteById(commentId);
    }
    /*
    поиск всех комментариев(для удаления при удалении статьи)
     */
    public List<CommentEntity> findAllCommentsOfArticle(Long articleId){
        return repository.getCommentsOfArticle(articleId);
    }

    public CommentEntity newComment(@NonNull String description, @NonNull UserEntity user, @NonNull ArticleEntity article){
        CommentEntity comment = new CommentEntity();
        comment.setArticle(article);
        comment.setUser(user);
        comment.setDescription(description);
        comment.setPublicationDate(new Date());
        return comment;
    }

    public CommentEntity newCommentWithDate(@NonNull String description, @NonNull UserEntity user, @NonNull ArticleEntity article,@NonNull Date publicationDate){
        CommentEntity comment = newComment(description, user, article);
        comment.setArticle(article);
        comment.setUser(user);
        comment.setDescription(description);
        comment.setPublicationDate(publicationDate);
        return comment;
    }
}
