package service;

import jwt.auth.JwtAuthentication;
import jwt.auth.JwtRequest;
import jwt.auth.JwtResponse;
import lombok.NonNull;

import javax.security.auth.message.AuthException;

public interface AuthService {

    JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException;

    JwtResponse getAccessToken(@NonNull String refreshToken);

    JwtResponse refresh(@NonNull String refreshToken) throws AuthException;

    JwtAuthentication getAuthInfo();
}
