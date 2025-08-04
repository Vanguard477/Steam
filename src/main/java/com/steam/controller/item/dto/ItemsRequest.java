package com.steam.controller.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ItemsRequest {
    @JsonProperty("descriptions")
    List<ItemRequest> items;
}
