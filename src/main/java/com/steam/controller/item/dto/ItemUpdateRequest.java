package com.steam.controller.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.steam.controller.description.dto.DescriptionUpdateRequest;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ItemUpdateRequest {
    @JsonProperty("classid")
    private String classId;
    @JsonProperty("instanceid")
    private String instanceId;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("name")
    private String name;
    @JsonProperty("market_name")
    private String marketName;
    @JsonProperty("descriptions")
    private List<DescriptionUpdateRequest> descriptions;
}
