package rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@EqualsAndHashCode
public class ArticleDTO {
    private Long id;
    private String title;
    private String description;
    private byte[] image;
    private UserDTO author;
    private List<CommentDTO> comments = new ArrayList<>();
    private Long likes;
    private boolean CurrentUserLikeIt;

    private Date date;
}
