package mapper;

import entity.CommentEntity;
import rest.dto.CommentDTO;

public interface CommentMapper {
    CommentDTO toCommentDTO(CommentEntity commentEntity);
}
