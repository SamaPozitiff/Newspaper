package mapper;

import entity.ArticleEntity;
import rest_dto.ArticleDTO;
import rest_dto.CommentDTO;

import java.io.IOException;
import java.util.List;


public interface ArticleMapper {
    ArticleDTO toArticleDto(ArticleEntity article, List<CommentDTO> comments,
                            Long numberOfLikes, boolean isUserLikeIt) throws IOException;
}
