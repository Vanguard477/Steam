package com.steam.controller.item.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class PriceRequest {
    private BigDecimal price;
}
