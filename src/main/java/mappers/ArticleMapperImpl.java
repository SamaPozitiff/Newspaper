package mappers;

import entities.ArticleEntity;
import org.springframework.stereotype.Component;
import restDTO.ArticleDTO;
import restDTO.CommentDTO;
import restDTO.LikeDTO;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Component
public class ArticleMapperImpl implements ArticleMapper{
    private final UserMapper userMapper;

    public ArticleMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ArticleDTO toArticleDto(ArticleEntity article, List<CommentDTO> comments, Long numberOfLikes, LikeDTO isUserLikeIt) throws IOException {
        if(article == null){
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setImage(imageToByteArray(article.getImage()));
        articleDTO.setTitle(article.getTitle());
        articleDTO.setAuthor(userMapper.toUserDTO(article.getAuthor()));
        articleDTO.setDescription(article.getDescription());
        articleDTO.setComments(comments);
        articleDTO.setLikes(numberOfLikes);
        articleDTO.setCurrentUserLikeIt(isUserLikeIt);
        return articleDTO;
    }


    private byte[] imageToByteArray (String path) throws IOException {

        File file = new File("src/main/resources/image1.png");
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return bytes;

    }


}
