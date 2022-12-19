package service;

import entity.ArticleEntity;

import entity.UserEntity;
import lombok.NonNull;
import repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository repository;



    public ArticleServiceImpl(ArticleRepository repository){
        this.repository = repository;
    }
    /*
    получение статей за последние 24 часа
     */
    public List<ArticleEntity> getAllArticlesFor24Hours(){
        return repository.findAllArticlesForLast24Hours();
    }
    /*
    сохранение статьи
     */
    public ArticleEntity save(ArticleEntity entity){
        return repository.save(entity);
    }
    /*
    поиск статьи по id
     */
    public ArticleEntity findById(Long id){
        return repository.findById(id).get();
    }

    public ArticleEntity newArticle(@NonNull String title, String image,@NonNull String description,@NonNull UserEntity user) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(title);
        article.setImage(image);
        article.setDescription(description);
        article.setAuthor(user);
        article.setPublicationDate(new Date());
        return article;
    }

    public ArticleEntity newArticleWithDate(@NonNull String title, String image, @NonNull String description, @NonNull UserEntity user, @NonNull Date date){
        ArticleEntity article = newArticle(title, image, description, user);
        article.setPublicationDate(date);
        return  article;
    }





}
