package com.steam.service.price;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class PriceService {
    private static final BigDecimal COMMISSION_PERCENT = new BigDecimal("0.1");

    public BigDecimal getFinalPrice(BigDecimal price) {
        log.info("Заданная стоимость: {} руб.", price);
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        return price.subtract(price.multiply(COMMISSION_PERCENT));
    }

}
