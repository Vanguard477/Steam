package com.steam.service.description.mapper;

import com.steam.controller.description.dto.DescriptionGetResponse;
import com.steam.controller.description.dto.DescriptionUpdateRequest;
import com.steam.domain.entity.Description;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DescriptionMapper {

    public static DescriptionGetResponse toDescriptionGetResponse(Description description) {
        return new DescriptionGetResponse()
                .setName(description.getName())
                .setType(description.getType())
                .setValue(description.getValue());
    }

    public static Description toDescription(DescriptionUpdateRequest descriptionUpdateRequestRequest) {
        return new Description()
                .setName(descriptionUpdateRequestRequest.getName())
                .setType(descriptionUpdateRequestRequest.getType())
                .setValue(descriptionUpdateRequestRequest.getValue());
    }
}
