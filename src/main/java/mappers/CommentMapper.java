package mappers;

import entities.CommentEntity;
import restDTO.CommentDTO;

public interface CommentMapper {
    CommentDTO toCommentDTO(CommentEntity commentEntity);
}
