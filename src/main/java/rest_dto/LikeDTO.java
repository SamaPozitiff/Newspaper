package rest_dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LikeDTO {
        private Long articleId;
        private Long userId;
}
