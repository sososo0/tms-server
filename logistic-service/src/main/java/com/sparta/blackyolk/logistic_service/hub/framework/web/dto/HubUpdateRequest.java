package com.sparta.blackyolk.logistic_service.hub.framework.web.dto;

import com.sparta.blackyolk.logistic_service.hub.persistence.vo.HubStatus;

public record HubUpdateRequest(
    Long hubManagerId,
    String name,
    HubStatus status,
    HubAddressUpdateRequest address
) {

}
