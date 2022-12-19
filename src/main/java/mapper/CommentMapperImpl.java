package mapper;

import entity.CommentEntity;
import org.springframework.stereotype.Component;
import rest.dto.CommentDTO;

@Component
public class CommentMapperImpl implements CommentMapper{
    private final UserMapper userMapper;

    public CommentMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public CommentDTO toCommentDTO(CommentEntity comment) {
       if(comment == null){
           return null;
       }
       CommentDTO commentDTO = new CommentDTO();
       commentDTO.setId(comment.getId());
       commentDTO.setDate(comment.getPublicationDate());
       commentDTO.setAuthor(userMapper.toUserDTO(comment.getUser()));
       commentDTO.setDescription(comment.getDescription());
       return commentDTO;
    }

}
