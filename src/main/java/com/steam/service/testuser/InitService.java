package com.steam.service.testuser;

import com.steam.domain.entity.Description;
import com.steam.domain.entity.Item;
import com.steam.domain.entity.User;
import com.steam.domain.repository.DescriptionRepository;
import com.steam.domain.repository.ItemRepository;
import com.steam.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {
    private final UserRepository userRepository;
    private final DescriptionRepository descriptionRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void creatTestUser() {
        User user = new User()
                .setBalance(new BigDecimal(100))
                .setSteamId("76561198130457732")
                .setItems(new ArrayList<>());
        Item item = new Item()
                .setUser(user)
                .setDescriptions(new ArrayList<>())
                .setName("1")
                .setInstanceId("1")
                .setIconUrl("1")
                .setClassId("3123")
                .setMarketName("1");
        Description description = new Description()
                .setType("1")
                .setName("1")
                .setValue("1");
        userRepository.save(user);
        itemRepository.save(item);
        descriptionRepository.save(description);
    }

    @Transactional
    public void clearDb() {
        userRepository.deleteAll();
    }
}
