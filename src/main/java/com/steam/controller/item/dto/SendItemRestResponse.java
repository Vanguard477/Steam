package com.steam.controller.item.dto;

import com.steam.controller.description.dto.SendDescriptionRestResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class SendItemRestResponse {
    private String steamId;
    private String classId;
    private String instanceId;
    private String iconUrl;
    private String name;
    private String marketName;
    private BigDecimal price;
    private List<SendDescriptionRestResponse> descriptions;
}
