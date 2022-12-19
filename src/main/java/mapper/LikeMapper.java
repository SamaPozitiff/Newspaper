package mapper;

import entity.LikeEntity;
import rest.dto.LikeDTO;


public interface LikeMapper {
    LikeDTO toLikeDTO(LikeEntity like);
}
