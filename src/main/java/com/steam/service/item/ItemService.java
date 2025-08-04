package com.steam.service.item;

import com.steam.controller.item.dto.ItemsRequest;
import com.steam.controller.item.dto.ItemsResponse;
import com.steam.domain.entity.Item;
import com.steam.domain.entity.User;
import com.steam.domain.repository.ItemRepository;
import com.steam.domain.repository.UserRepository;
import com.steam.service.description.mapper.DescriptionMapper;
import com.steam.service.item.mapper.ItemMapper;
import com.steam.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public void updateUserItems(ItemsRequest itemsRequest, String steamId) {
        var user = userService.getUserBySteamId(steamId);

        for (var itemRequest : itemsRequest.getItems()) {
            var descriptions = itemRequest.getDescriptions()
                    .stream()
                    .map(DescriptionMapper::toDescription)
                    .toList();

            if (!checkExistUserItemByClassId(itemRequest.getClassId(), user)) {
                var item = new Item()
                        .setUser(user)
                        .setClassId(itemRequest.getClassId())
                        .setName(itemRequest.getName())
                        .setInstanceId(itemRequest.getInstanceId())
                        .setIconUrl(itemRequest.getIconUrl())
                        .setMarketName(itemRequest.getMarketName())
                        .setDescriptions(descriptions);

                user.getItems().add(item);
                itemRepository.save(item);
            }
        }
        userRepository.save(user);
    }

    public ItemsResponse getAllUserItems(String steamId) {
        var userItems = userService.getUserBySteamId(steamId).getItems();
        return new ItemsResponse()
                .setItems(userItems
                        .stream()
                        .map(ItemMapper::toItemResponse)
                        .toList());
    }

    private boolean checkExistUserItemByClassId(String classId, User user) {
        return user.getItems().stream()
                .anyMatch(item -> classId.equals(item.getClassId()));
    }


}
