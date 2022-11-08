package services;

import entities.CommentEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class CommentService {
    CommentRepository repository;
    public CommentService(CommentRepository repository){
        this.repository = repository;
    }

    public List<CommentEntity> getPageCommentsOfArticle(Long articleId, int page){
        Pageable pageable = (Pageable) PageRequest.of(page, 5);
        return repository.getCommentsOfArticle(articleId, pageable);

    }

    public void save(CommentEntity comment){
        repository.save(comment);
    }
}
