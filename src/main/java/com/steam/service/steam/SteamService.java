package com.steam.service.steam;

import com.steam.controller.item.dto.UpdateItemsRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class SteamService {
    private static final String STEAM_API_URL = "https://steamcommunity.com";

    public UpdateItemsRequest getItemsRequestBySteamId(String steamId) {
        var restTemplate = new RestTemplate();
        String url = STEAM_API_URL +
                "/inventory/" + steamId +
                "/730/2";

        return restTemplate.getForObject(url, UpdateItemsRequest.class);
    }

}
