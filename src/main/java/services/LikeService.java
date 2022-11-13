package services;

import entities.ArticleEntity;
import entities.LikeEntity;
import entities.UserEntity;
import repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    LikeRepository repository;
    public LikeService(LikeRepository repository){
        this.repository = repository;
    }

    public void addLike(ArticleEntity article, UserEntity user){
        repository.save(new LikeEntity(article, user));
    }

    public long getAmountLikesFromArticle(long id){
        return repository.getAmountLikesFromArticle(id);
    }

    public boolean isUserLikeThisArticle(long userId, long articleId){
        return repository.isUserLikeThisArticle(userId, articleId) > 0 ? true : false;
    }

    public LikeEntity getLike(Long id){
        return repository.findById(id).get();
    }

    public LikeEntity save (LikeEntity like){
        return repository.save(like);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Long getLikeId(Long  userId, Long articleId){
        return repository.getLikeId(userId, articleId);
    }
}
