package services;

import entities.CommentEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final int COMMENTS_ON_PAGE = 3;
    CommentRepository repository;

    public CommentService(CommentRepository repository){
        this.repository = repository;
    }
    /*
    сохранение комментария
     */
    public void save(CommentEntity comment){
        repository.save(comment);
    }
    /*
    найти все комментарии к конкретной статье постранично
     */
    public List<CommentEntity> findCommentsOfArticle(Integer page, Long article){
        return  repository.getCommentsOfArticle(article).stream().skip((long) page * COMMENTS_ON_PAGE).limit(COMMENTS_ON_PAGE).collect(Collectors.toList());
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
}
