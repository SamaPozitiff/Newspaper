package mapper;

import entity.LikeEntity;
import rest_dto.LikeDTO;


public interface LikeMapper {
    LikeDTO toLikeDTO(LikeEntity like);
}
