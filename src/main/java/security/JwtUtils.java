package security;

import entities.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUtils {
    @Autowired
    UserEntity user;
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRole(getRole(claims).toString());
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<String> getRole(Claims claims) {
        final List<String> role = claims.get("role", List.class);
        return role.stream()
                .collect(Collectors.toSet());
    }
}
