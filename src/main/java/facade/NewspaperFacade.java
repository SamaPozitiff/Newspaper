package facade;

import rest.dto.ArticleDTO;
import rest.dto.CommentDTO;

import java.io.IOException;
import java.util.List;

public interface NewspaperFacade {

    List<ArticleDTO> homepage() throws IOException;

    List<CommentDTO> getCommentsOnArticle(int page, Long articleId);

    void addComment(String description, Long articleId);

    void likeArticle(Long articleId);
}
