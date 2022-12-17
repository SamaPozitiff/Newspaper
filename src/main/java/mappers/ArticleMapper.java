package mappers;

import entities.ArticleEntity;
import org.mapstruct.Mapper;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import restDTO.LikeDTO;

import java.io.IOException;
import java.util.List;


public interface ArticleMapper {
    ArticleDTO toArticleDto(ArticleEntity article, List<CommentDTO> comments,
                            Long numberOfLikes, boolean isUserLikeIt) throws IOException;
}
