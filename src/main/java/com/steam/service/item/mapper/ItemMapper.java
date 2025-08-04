package com.steam.service.item.mapper;

import com.steam.controller.item.dto.ItemResponse;
import com.steam.domain.entity.Item;
import com.steam.service.description.mapper.DescriptionMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static ItemResponse toItemResponse(Item item) {
        var descriptions = item.getDescriptions()
                .stream()
                .map(DescriptionMapper::toDescriptionResponse)
                .toList();
        return new ItemResponse()
                .setClassId(item.getClassId())
                .setName(item.getName())
                .setInstanceId(item.getInstanceId())
                .setIconUrl(item.getIconUrl())
                .setMarketName(item.getMarketName())
                .setDescriptions(descriptions);
    }
}
