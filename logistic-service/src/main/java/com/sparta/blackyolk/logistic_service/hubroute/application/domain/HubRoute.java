package com.sparta.blackyolk.logistic_service.hubroute.application.domain;

import com.sparta.blackyolk.logistic_service.hub.application.domain.Hub;
import com.sparta.blackyolk.logistic_service.hubroute.data.vo.HubRouteStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HubRoute {

    private String hubRouteId;
    private Hub departureHub;
    private Hub arrivalHub;
    private HubRouteStatus status;
    private String timeSlot;
    private Integer duration;
    private BigDecimal distance;
    private double timeSlotWeight;

    public boolean isDepartureHubBelongToHubRoute(String departureHubId) {
        return departureHubId.equals(departureHub.getHubId());
    }
}
