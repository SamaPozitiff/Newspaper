package mappers;

import entities.ArticleEntity;
import org.springframework.stereotype.Component;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import restDTO.LikeDTO;

import java.util.List;
@Component
public class ArticleMapperImpl implements ArticleMapper{
    private final UserMapper userMapper;

    public ArticleMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ArticleDTO toArticleDto(ArticleEntity article, List<CommentDTO> comments, Long numberOfLikes, LikeDTO isUserLikeIt) {
        if(article == null){
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setImage(article.getImage());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setAuthor(userMapper.toUserDTO(article.getAuthor()));
        articleDTO.setDescription(article.getDescription());
        articleDTO.setComments(comments);
        articleDTO.setLikes(numberOfLikes);
        articleDTO.setCurrentUserLikeIt(isUserLikeIt);
        return articleDTO;
    }



}
