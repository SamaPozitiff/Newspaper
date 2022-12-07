package mappers;

import entities.LikeEntity;
import org.springframework.stereotype.Component;
import restDTO.LikeDTO;
@Component
public class LikeMapperImpl implements LikeMapper{

    @Override
    public LikeDTO toLikeDTO(LikeEntity like) {
        if(like == null){
            return null;
        }
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setArticleId(like.getArticle().getId());
        likeDTO.setUserId(like.getUser().getId());
        return likeDTO;
    }
}
