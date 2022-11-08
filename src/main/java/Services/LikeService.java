package Services;

import Entities.ArticleEntity;
import Entities.LikeEntity;
import Entities.UserEntity;
import Repositories.LikeRepository;
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

    public void save (LikeEntity like){
        repository.save(like);
    }
}
