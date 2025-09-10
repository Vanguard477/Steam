package com.steam.service.item;

import com.steam.controller.item.dto.UpdateItemsRequest;
import com.steam.controller.item.dto.GetInventoryItemsResponse;
import com.steam.domain.entity.Item;
import com.steam.domain.entity.User;
import com.steam.domain.repository.ItemRepository;
import com.steam.domain.repository.UserRepository;
import com.steam.service.description.mapper.DescriptionMapper;
import com.steam.service.item.mapper.ItemMapper;
import com.steam.service.price.PriceService;
import com.steam.service.rest.ApiSendItem;
import com.steam.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PriceService priceService;
    private final ApiSendItem apiSendItem;

    public void updateUserItems(UpdateItemsRequest updateItemsRequest, String steamId) {
        var user = userService.getUserBySteamId(steamId);

        for (var itemUpdateRequest : updateItemsRequest.getItems()) {
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

    public GetInventoryItemsResponse getAllUserItems(String steamId) {
        var userItems = userService.getUserBySteamId(steamId).getItems();
        return new GetInventoryItemsResponse()
                .setItems(userItems
                        .stream()
                        .map(ItemMapper::toItemGetResponse)
                        .toList());
    }

    private boolean checkExistUserItemByClassId(String classId, User user) {
        return user.getItems().stream()
                .anyMatch(item -> classId.equals(item.getClassId()));
    }

    public void sendItemInMarketByRest(String classId, BigDecimal price, User user) {
            var item = getItemUserByClassId(classId, user);
            var finalPrice = priceService.getFinalPrice(price);
           apiSendItem.sendItemInMarket(ItemMapper.toItemSendResponse(item, finalPrice));
    }

    private Item getItemUserByClassId(String classId, User user) {
        return itemRepository.findItemByUserAndClassId(user, classId)
                .orElseThrow(()-> new RuntimeException("Не удалось найти предмет под идентификатору: " + classId + " у пользователя: " + user.getSteamId()));
    }

}
