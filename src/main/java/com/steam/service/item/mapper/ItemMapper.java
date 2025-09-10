package com.steam.service.item.mapper;

import com.steam.controller.item.dto.GetInventoryItemResponse;
import com.steam.controller.item.dto.SendItemRestResponse;
import com.steam.domain.entity.Item;
import com.steam.service.description.mapper.DescriptionMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static GetInventoryItemResponse toItemGetResponse(Item item) {
        var descriptions = item.getDescriptions()
                .stream()
                .map(DescriptionMapper::toDescriptionGetResponse)
                .toList();
        return new GetInventoryItemResponse()
                .setClassId(item.getClassId())
                .setInstanceId(item.getInstanceId())
                .setIconUrl(item.getIconUrl())
                .setName(item.getName())
                .setMarketName(item.getMarketName())
                .setDescriptions(descriptions);
    }

    public static SendItemRestResponse toItemSendResponse(Item item, BigDecimal price) {
        var descriptions = item.getDescriptions()
                .stream()
                .map(DescriptionMapper::toSendDescriptionResponse)
                .toList();
        return new SendItemRestResponse()
                .setSteamId(item.getUser().getSteamId())
                .setClassId(item.getClassId())
                .setInstanceId(item.getInstanceId())
                .setIconUrl(item.getIconUrl())
                .setName(item.getName())
                .setMarketName(item.getMarketName())
                .setPrice(price)
                .setDescriptions(descriptions);
    }
}
