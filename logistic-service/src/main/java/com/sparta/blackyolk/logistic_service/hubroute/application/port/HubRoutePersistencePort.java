package com.sparta.blackyolk.logistic_service.hubroute.application.port;

import com.sparta.blackyolk.logistic_service.hub.application.domain.Hub;
import com.sparta.blackyolk.logistic_service.hubroute.application.domain.HubRoute;
import com.sparta.blackyolk.logistic_service.hubroute.application.domain.HubRouteForDelete;
import com.sparta.blackyolk.logistic_service.hubroute.application.domain.HubRouteForUpdate;
import java.math.BigDecimal;
import java.util.Optional;

public interface HubRoutePersistencePort {
    HubRoute createHubRoute(Long userId, HubRoute hubRoute);
    Optional<HubRoute> findByHubRouteId(String hubRouteId);
    HubRoute updateHubRoute(HubRouteForUpdate hubRouteForUpdate, Hub arrivalHub, BigDecimal distance, Integer duration);
    HubRoute deleteHubRoute(HubRouteForDelete hubRouteForDelete);
}
