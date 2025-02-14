package com.sparta.blackyolk.logistic_service.hub.framework.web.dto;

import com.sparta.blackyolk.logistic_service.hub.application.domain.Hub;
import com.sparta.blackyolk.logistic_service.hub.data.vo.HubStatus;

public record HubGetResponse(
    String id,
    String hubManagerId,
    String name,
    String center,
    HubStatus status,
    HubCoordinateResponse coordinate,
    HubAddressResponse address
) {

    public static HubGetResponse toDTO(Hub hub) {
        return new HubGetResponse(
            hub.getHubId(),
            hub.getHubManagerId(),
            hub.getHubName(),
            hub.getHubCenter(),
            hub.getHubStatus(),
            HubCoordinateResponse.fromDomain(hub.getHubCoordinate()),
            HubAddressResponse.fromDomain(hub.getHubAddress())
        );
    }
}
