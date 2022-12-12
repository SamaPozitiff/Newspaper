package restDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
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
