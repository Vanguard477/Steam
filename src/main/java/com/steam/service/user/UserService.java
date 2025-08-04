package com.steam.service.user;

import com.steam.domain.entity.User;
import com.steam.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserBySteamId(String steamId) {
        return userRepository.findBySteamId(steamId)
                .orElseThrow(() -> new RuntimeException("Пользователь по стим идентификатору: " + steamId + " не найден"));
    }

}
