package mappers;

import entities.LikeEntity;
import org.mapstruct.Mapper;
import restDTO.LikeDTO;


public interface LikeMapper {
    LikeDTO toLikeDTO(LikeEntity like);
}
