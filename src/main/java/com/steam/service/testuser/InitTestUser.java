package com.steam.service.testuser;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitTestUser {
    private final InitService initService;

    @PostConstruct
    public void creatTestUsers() {
        initService.creatTestUser();
    }

    @PreDestroy
    public void destroyTestUsers() {
        initService.clearDb();
    }
}
