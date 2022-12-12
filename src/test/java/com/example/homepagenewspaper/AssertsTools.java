package com.example.homepagenewspaper;

import entities.ArticleEntity;
import entities.CommentEntity;
import entities.UserEntity;
import org.springframework.stereotype.Component;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import restDTO.UserDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Component
public class AssertsTools {

    public boolean compareArticles(List<ArticleEntity> expect, List<ArticleEntity> result){
        if (expect.size()!=result.size()){
            return false;
        }
        for (int i = 0; i < expect.size(); i++){
            if(!compareArticle(expect.get(i), result.get(i))) return false;
        }
        return true;
    }

    public boolean compareUser(UserEntity expect, UserEntity result){
        if (!Objects.equals(expect.getId(), result.getId())) return false;
        if (!Objects.equals(expect.getEmail(), result.getEmail())) return false;
        if (!Objects.equals(expect.getFirstName(), result.getFirstName())) return false;
        if (!Objects.equals(expect.getLastName(), result.getLastName())) return false;
        if (!Objects.equals(expect.getRole(), result.getRole())) return false;
        if (!Objects.equals(expect.getPassword(), result.getPassword())) return false;
        return true;
    }

    public boolean compareComment(CommentEntity expect, CommentEntity result){
        if(expect == null || result == null) return false;
        if (!Objects.equals(expect.getId(), result.getId())) return false;
        if (!Objects.equals(expect.getDescription(), result.getDescription())) return false;
        if (!compareArticle(expect.getArticle(), result.getArticle())) return false;
        if (!compareUser(expect.getUser(), result.getUser())) return false;
        return true;
    }

    public boolean compareCommentDTO(CommentDTO expect, CommentDTO result){
        if(!Objects.equals(expect.getId(), result.getId())) return false;
        if(!compareUserDTO(expect.getAuthor(), result.getAuthor())) return false;
        if(!Objects.equals(expect.getDescription(), result.getDescription())) return false;
        if(!Objects.equals(expect.getDate(), result.getDate())) return false;
        return true;
    }

    public boolean compareListCommentDTO(List<CommentDTO> expect, List<CommentDTO> result){
        if(expect.size()!= result.size()) return false;
        for (int i = 0; i < expect.size(); i++){
            if (!compareCommentDTO(expect.get(i), result.get(i))) return false;
        }
        return true;
    }

    public boolean compareUserDTO(UserDTO expect, UserDTO result){
        if(!Objects.equals(expect.getId(), result.getId())) return false;
        if(!Objects.equals(expect.getFullName(), result.getFullName())) return false;
        return true;
    }

    public boolean compareArticle(ArticleEntity expect, ArticleEntity result){
        if (!Objects.equals(expect.getId(), result.getId())) return false;
        if (!Objects.equals(expect.getTitle(), result.getTitle())) return false;
        if (!Objects.equals(expect.getImage(), result.getImage())) return false;
        if (!compareUser(expect.getAuthor(), result.getAuthor())) return false;
        if (!Objects.equals(expect.getDescription(), result.getDescription())) return false;
        if (!Objects.equals(expect.getPublicationDate(), result.getPublicationDate())) return false;
        return true;
    }

    public boolean compareArticleDTO(ArticleDTO expect, ArticleDTO result){
        if (!Objects.equals(expect.getId(), result.getId())) return false;
        if (!compareUserDTO(expect.getAuthor(), result.getAuthor())) return false;
        if (!Objects.equals(expect.getTitle(), result.getTitle())) return false;
        if (!Objects.equals(expect.getLikes(), result.getLikes())) return false;
        if (!compareListCommentDTO(expect.getComments(), result.getComments())) return false;
        return true;
    }

    public boolean compareListArticleDTO(List<ArticleDTO> expect, List<ArticleDTO> result){
        if(expect.size()!= result.size()) return false;
        for (int i = 0; i < expect.size(); i++){
            if (!compareArticleDTO(expect.get(i), result.get(i))) return false;
        }
        return true;
    }

    public boolean compareListOfComments(List<CommentEntity> expect, List<CommentEntity> result){
        if(expect.size()!= result.size()) return false;
        for (int i = 0; i < expect.size(); i++){
            if(!compareComment(expect.get(i), result.get(i))) return false;
        }
        return true;
    }
}
