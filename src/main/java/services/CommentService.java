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


    public void save(CommentEntity comment){
        repository.save(comment);
    }

    public List<CommentEntity> findCommentsOfArticle(Integer page, Long article){
        return  repository.getCommentsOfArticle(article).stream().skip((long) page * COMMENTS_ON_PAGE).limit(COMMENTS_ON_PAGE).collect(Collectors.toList());
    }
}
