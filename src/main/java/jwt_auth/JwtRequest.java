package jwt_auth;

import lombok.Data;

@Data
public class JwtRequest {

    private String email;
    private String password;

}
