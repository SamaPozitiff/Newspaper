package mapper;

import entity.ArticleEntity;
import rest.dto.ArticleDTO;
import rest.dto.CommentDTO;

import java.io.IOException;
import java.util.List;


public interface ArticleMapper {
    ArticleDTO toArticleDto(ArticleEntity article, List<CommentDTO> comments,
                            Long numberOfLikes, boolean isUserLikeIt) throws IOException;
}
