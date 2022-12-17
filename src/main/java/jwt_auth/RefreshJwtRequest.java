package jwt_auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJwtRequest {
    public String refreshToken;
}
