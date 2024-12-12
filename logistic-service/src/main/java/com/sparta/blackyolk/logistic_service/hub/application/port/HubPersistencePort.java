package com.sparta.blackyolk.logistic_service.hub.application.port;

import com.sparta.blackyolk.logistic_service.hub.application.domain.Hub;
import com.sparta.blackyolk.logistic_service.hub.application.domain.HubForCreate;
import com.sparta.blackyolk.logistic_service.hub.application.domain.HubForUpdate;
import java.math.BigDecimal;
import java.util.Optional;

public interface HubPersistencePort {
    Hub saveHub(HubForCreate hubForCreate, BigDecimal axisX, BigDecimal axisY);
    Optional<Hub> findByHubId(String hubId);
    Hub updateHub(HubForUpdate hubForUpdate, BigDecimal axisX, BigDecimal axisY);
}
