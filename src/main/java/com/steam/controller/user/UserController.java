package com.steam.controller.user;

import com.steam.controller.item.dto.GetInventoryItemsResponse;
import com.steam.controller.item.dto.PriceRequest;
import com.steam.service.item.ItemService;
import com.steam.service.steam.SteamService;
import com.steam.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user-cab/inventory")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final SteamService steamService;
    private final ItemService itemService;
    private final UserService userService;

    @Operation(summary = "Обновить инвентарь предметов текущего пользователя")
    @GetMapping("/update")
    public void updateInventory() {
        var currentUserSteamId = userService.getCurrentUser().getSteamId();
        itemService.updateUserItems(steamService.getItemsRequestBySteamId(currentUserSteamId), currentUserSteamId);
    }

    @Operation(summary = "Получение предметов теущего пользователя")
    @GetMapping()
    public GetInventoryItemsResponse getInventory() {
        var currentUserSteamId = userService.getCurrentUser().getSteamId();
        return itemService.getAllUserItems(currentUserSteamId);
    }

    @Operation(summary = "Отправка предмета на продажу в маркет")
    @PutMapping("/{classId}")
    public void addItemInMarket(@PathVariable String classId, @RequestBody PriceRequest priceRequest) {
        var currentUser = userService.getCurrentUser();
        itemService.sendItemInMarketByRest(classId, priceRequest.getPrice(), currentUser);
    }

}
