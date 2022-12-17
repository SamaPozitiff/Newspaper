package rest_dto;

import lombok.Data;

import java.util.Date;
@Data
public class CommentDTO {
    private Long id;
    private String description;
    private UserDTO author;
    private Date date;
}
