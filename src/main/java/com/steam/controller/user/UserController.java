package com.steam.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.steam.controller.item.dto.ItemsResponse;
import com.steam.service.item.ItemService;
import com.steam.service.steam.SteamService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/inventory")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final SteamService steamService;
    private final ItemService itemService;

    @Operation(summary = "Обновить инвентарь предметов пользователя по стим идентификатору")
    @GetMapping("/{steamId}/update")
    public void updateInventory(@PathVariable String steamId) {
        itemService.updateUserItems(steamService.getItemsRequestBySteamId(steamId), steamId);
    }

    @Operation(summary = "Получение предметов пользователя по стим идентификатору")
    @GetMapping("/{steamId}")
    public ItemsResponse getInventory(@PathVariable String steamId) {
        return itemService.getAllUserItems(steamId);
    }

}
