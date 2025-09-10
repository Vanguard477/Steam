package com.steam.service.auth;

import com.steam.controller.auth.dto.AccessJwtAuthResponse;
import com.steam.domain.entity.User;
import com.steam.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    /**
     * Аутентификация пользователя
     */
    public AccessJwtAuthResponse signIn(User user) {
        var jwt = jwtService.generateAccessToken(user);
        log.info("Выдан токен: {}", jwt);
        return new AccessJwtAuthResponse(jwt);
    }
}
