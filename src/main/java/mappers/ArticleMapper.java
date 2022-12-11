package mappers;

import entities.ArticleEntity;
import org.mapstruct.Mapper;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import restDTO.LikeDTO;

import java.io.IOException;
import java.util.List;

@Mapper
public interface ArticleMapper {
    ArticleDTO toArticleDto(ArticleEntity article, List<CommentDTO> comments, Long numberOfLikes, LikeDTO isUserLikeIt) throws IOException;
}
