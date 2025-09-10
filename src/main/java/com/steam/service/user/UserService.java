package com.steam.service.user;

import com.steam.domain.entity.User;
import com.steam.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findOrCreateUserBySteamId(String steamId) {
        return userRepository.findBySteamId(steamId)
                .orElseGet(() -> {
                    var user = new User()
                            .setSteamId(steamId)
                            .setItems(new ArrayList<>())
                            .setBalance(new BigDecimal("0"));
                    return userRepository.save(user);
                });
    }

    public User getUserBySteamId(String steamId) {
        return userRepository.findBySteamId(steamId)
                .orElseThrow(() -> new RuntimeException("Пользователь по стим идентификатору: " + steamId + " не найден"));
    }

    public User getCurrentUser() {
        var currentSteamId = getCurrentSteamId();
        log.info("Текущий идетификатор стима пользователя: {}", currentSteamId);
        return getUserBySteamId(currentSteamId);
    }

    private String getCurrentSteamId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
