package com.steam.service.rest;

import com.steam.controller.item.dto.SendItemRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "item", url = "http://localhost:8081")
public interface ApiSendItem {

    @PutMapping("/user-cab/inventory/send")
    void sendItemInMarket(@RequestBody SendItemRestResponse sendItemRestResponse);

}
