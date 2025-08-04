package com.steam.service.steam;

import com.steam.controller.item.dto.ItemsRequest;
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

    public ItemsRequest getItemsRequestBySteamId(String steamId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = STEAM_API_URL +
                "/inventory/" + steamId +
                "/730/2";

        return restTemplate.getForObject(url, ItemsRequest.class);
    }

}
