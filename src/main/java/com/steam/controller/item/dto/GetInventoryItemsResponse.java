package com.steam.controller.item.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetInventoryItemsResponse {
    private List<GetInventoryItemResponse> items;
}
