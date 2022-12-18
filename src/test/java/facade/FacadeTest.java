package facade;

import jwt_auth.SecurityConfig;
import mapper.*;
import newspaper_main.AssertsTools;
import newspaper_main.HomePageNewsPaperApplication;
import entity.ArticleEntity;
import entity.CommentEntity;
import entity.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import rest_dto.ArticleDTO;
import rest_dto.CommentDTO;
import service.ArticleService;
import service.CommentService;
import service.LikeService;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class FacadeTest {


    @ParameterizedTest
    @CsvSource({
            "1, 3",
            "5, 1",
            "4, 2"
    })
    void testHomage(int articles, int comments) throws IOException {
        ArticleService articleService = Mockito.mock(ArticleService.class);
        CommentService commentService = Mockito.mock(CommentService.class);
        LikeService likeService = Mockito.mock(LikeService.class);
        UserService userService = Mockito.mock(UserService.class);
        SecurityConfig securityConfig = Mockito.mock(SecurityConfig.class);
        ArticleMapper articleMapper = Mockito.mock(ArticleMapperImpl.class);
        CommentMapper commentMapper = Mockito.mock(CommentMapperImpl.class);
        NewspaperFacade newspaperFacade = new NewspaperFacade(articleService, commentService, likeService, userService, securityConfig, articleMapper, commentMapper);
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
