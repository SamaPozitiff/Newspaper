package security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JwtRequest {
    private String email;
    private String password;

}
