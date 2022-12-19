package facade;

import jwt.auth.SecurityConfig;
import mapper.*;
import newspaper.main.HomePageNewsPaperApplication;
import entity.ArticleEntity;
import entity.CommentEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class FacadeTest {


    @ParameterizedTest
    @CsvSource({
            "1, 3",
            "5, 1",
            "4, 2"
    })
    void testHomage(int articles, int comments) throws IOException {
        ArticleService articleService = Mockito.mock(ArticleServiceImpl.class);
        CommentService commentService = Mockito.mock(CommentServiceImpl.class);
        LikeService likeService = Mockito.mock(LikeServiceImpl.class);
        UserService userService = Mockito.mock(UserServiceImpl.class);
        SecurityConfig securityConfig = Mockito.mock(SecurityConfig.class);
        ArticleMapper articleMapper = Mockito.mock(ArticleMapperImpl.class);
        CommentMapper commentMapper = Mockito.mock(CommentMapperImpl.class);
        NewspaperFacade newspaperFacade = new NewspaperFacadeImpl(articleService, commentService, likeService, userService, securityConfig, articleMapper, commentMapper);
        List<ArticleEntity> articlesList = new ArrayList<>();
        int i = 0;
        while (i < articles){
            articlesList.add(Mockito.mock(ArticleEntity.class));
            i++;
        }
        List<CommentEntity> commentsList = new ArrayList<>();
        int j = 0;
        while (j < comments){
            commentsList.add(Mockito.mock(CommentEntity.class));
            j++;
        }
        Mockito.when(articleService.getAllArticlesFor24Hours()).thenReturn(articlesList);
        Mockito.when(commentService.findPagedCommentsOfArticle(Mockito.any(), Mockito.any())).thenReturn(commentsList);
        newspaperFacade.homepage();
        Mockito.verify(articleService, Mockito.times(1)).getAllArticlesFor24Hours();
        Mockito.verify(commentService, Mockito.times(articles)).findPagedCommentsOfArticle(Mockito.any(), Mockito.any());
        Mockito.verify(commentMapper, Mockito.times(articles*comments)).toCommentDTO(Mockito.any());
        Mockito.verify(articleMapper, Mockito.times(articles)).toArticleDto(Mockito.any(), Mockito.anyList(), Mockito.anyLong(), Mockito.anyBoolean());

    }
}
