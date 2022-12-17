package mapper;

import entity.CommentEntity;
import rest_dto.CommentDTO;

public interface CommentMapper {
    CommentDTO toCommentDTO(CommentEntity commentEntity);
}
