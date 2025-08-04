package com.steam.service.description.mapper;

import com.steam.controller.description.dto.DescriptionRequest;
import com.steam.controller.description.dto.DescriptionResponse;
import com.steam.domain.entity.Description;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DescriptionMapper {

    public static DescriptionResponse toDescriptionResponse(Description description) {
        return new DescriptionResponse()
                .setName(description.getName())
                .setType(description.getType())
                .setValue(description.getValue());
    }

    public static Description toDescription(DescriptionRequest descriptionRequest) {
        return new Description()
                .setName(descriptionRequest.getName())
                .setType(descriptionRequest.getType())
                .setValue(descriptionRequest.getValue());
    }
}
