package com.steam.controller.item.dto;

import com.steam.controller.description.dto.DescriptionResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ItemResponse {
    private String classId;
    private String instanceId;
    private String iconUrl;
    private String name;
    private String marketName;
    private List<DescriptionResponse> descriptions;
}
