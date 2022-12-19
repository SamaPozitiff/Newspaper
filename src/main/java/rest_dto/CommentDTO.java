package rest_dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Data
@EqualsAndHashCode
public class CommentDTO {
    private Long id;
    private String description;
    private UserDTO author;
    private Date date;
}
