package com.steam.service.item;

import com.steam.controller.item.dto.ItemsUpdateRequest;
import com.steam.controller.item.dto.ItemsGetResponse;
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

    public void updateUserItems(ItemsUpdateRequest itemsUpdateRequest, String steamId) {
        var user = userService.getUserBySteamId(steamId);

        for (var itemUpdateRequest : itemsUpdateRequest.getItems()) {
            var descriptions = itemUpdateRequest.getDescriptions()
                    .stream()
                    .map(DescriptionMapper::toDescription)
                    .toList();

            if (!checkExistUserItemByClassId(itemUpdateRequest.getClassId(), user)) {
                var item = new Item()
                        .setUser(user)
                        .setClassId(itemUpdateRequest.getClassId())
                        .setName(itemUpdateRequest.getName())
                        .setInstanceId(itemUpdateRequest.getInstanceId())
                        .setIconUrl(itemUpdateRequest.getIconUrl())
                        .setMarketName(itemUpdateRequest.getMarketName())
                        .setDescriptions(descriptions);

                user.getItems().add(item);
                itemRepository.save(item);
            }
        }
        userRepository.save(user);
    }

    public ItemsGetResponse getAllUserItems(String steamId) {
        var userItems = userService.getUserBySteamId(steamId).getItems();
        return new ItemsGetResponse()
                .setItems(userItems
                        .stream()
                        .map(ItemMapper::toItemGetResponse)
                        .toList());
    }

    private boolean checkExistUserItemByClassId(String classId, User user) {
        return user.getItems().stream()
                .anyMatch(item -> classId.equals(item.getClassId()));
    }


}
