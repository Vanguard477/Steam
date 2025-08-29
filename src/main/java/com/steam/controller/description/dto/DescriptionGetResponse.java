package com.steam.controller.description.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DescriptionGetResponse {
    private String type;
    private String value;
    private String name;
}
