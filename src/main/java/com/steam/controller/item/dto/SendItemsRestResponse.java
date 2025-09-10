package com.steam.controller.item.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SendItemsRestResponse {
    private List<SendItemsRestResponse> items;
}
