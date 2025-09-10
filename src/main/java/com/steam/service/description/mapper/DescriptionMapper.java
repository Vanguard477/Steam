package com.steam.service.description.mapper;

import com.steam.controller.description.dto.GetDescriptionResponse;
import com.steam.controller.description.dto.SendDescriptionRestResponse;
import com.steam.controller.description.dto.UpdateDescriptionRequest;
import com.steam.domain.entity.Description;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DescriptionMapper {

    public static GetDescriptionResponse toDescriptionGetResponse(Description description) {
        return new GetDescriptionResponse()
                .setName(description.getName())
                .setType(description.getType())
                .setValue(description.getValue());
    }

    public static Description toDescription(UpdateDescriptionRequest updateRequestDescriptionRequest) {
        return new Description()
                .setName(updateRequestDescriptionRequest.getName())
                .setType(updateRequestDescriptionRequest.getType())
                .setValue(updateRequestDescriptionRequest.getValue());
    }

    public static SendDescriptionRestResponse toSendDescriptionResponse(Description description) {
        return new SendDescriptionRestResponse()
                .setName(description.getName())
                .setType(description.getType())
                .setValue(description.getValue());
    }
}
