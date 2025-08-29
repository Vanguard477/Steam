package com.steam.service.item.mapper;

import com.steam.controller.item.dto.ItemGetResponse;
import com.steam.domain.entity.Item;
import com.steam.service.description.mapper.DescriptionMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static ItemGetResponse toItemGetResponse(Item item) {
        var descriptions = item.getDescriptions()
                .stream()
                .map(DescriptionMapper::toDescriptionGetResponse)
                .toList();
        return new ItemGetResponse()
                .setClassId(item.getClassId())
                .setName(item.getName())
                .setInstanceId(item.getInstanceId())
                .setIconUrl(item.getIconUrl())
                .setMarketName(item.getMarketName())
                .setDescriptions(descriptions);
    }
}
