package com.sparta.blackyolk.logistic_service.hub.framework.web.controller;

import com.sparta.blackyolk.logistic_service.common.pagenation.PaginationConstraint;
import com.sparta.blackyolk.logistic_service.hub.application.service.HubCacheService;
import com.sparta.blackyolk.logistic_service.hub.data.HubEntity;
import com.sparta.blackyolk.logistic_service.hub.framework.repository.HubRepository;
import com.sparta.blackyolk.logistic_service.hub.framework.web.dto.HubGetResponse;
import com.sparta.blackyolk.logistic_service.hub.framework.web.dto.HubPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hubs")
public class HubQueryController {

    private final HubCacheService hubCacheService;
    private final HubRepository hubRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{hubId}")
    public HubGetResponse getHub(
        @RequestHeader(value = "X-User-Id", required = true) String userId,
        @RequestHeader(value = "X-Role", required = true) String role,
        @PathVariable(value = "hubId") String hubId
    ) {
        return hubCacheService.getHub(hubId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @PaginationConstraint(defaultSort = "createdAt", defaultDirection = "DESC", availableSize = {10,30,50}, defaultSize = 10)
    public HubPageResponse getHubs(
        @RequestHeader(value = "X-User-Id", required = true) String userId,
        @RequestHeader(value = "X-Role", required = true) String role,
        Pageable pageable,
        @RequestParam(required = false) String keyword
    ) {
        log.info("[Hub search 조회 pageable] : {}", pageable);

        return hubCacheService.getHubs(pageable, keyword);
    }

    @GetMapping("/isAdmin")
    public boolean isHubAdmin(@RequestParam("hubId") String hubId, @RequestParam("userName") String userName) {
        log.info("허브 서비스 요청 수신: hubId={}, userName={}", hubId, userName);

        HubEntity hubEntity = hubRepository.findById(hubId)
                .orElseThrow(() -> new IllegalArgumentException("허브를 찾을 수 없습니다: " + hubId));
        boolean isAdmin = hubEntity.getHubManagerId().equals(userName);

//        log.info("허브 관리자 확인 결과: hubId={}, userName={}, isAdmin={}", hubId, userName, isAdmin);
        System.out.println("허브 서비스 요청 수신: hubId=" + hubId + ", userName=" + userName);
        System.out.println("허브 관리자 확인 결과: hubId=" + hubId + ", userName=" + userName + ", isAdmin=" + isAdmin);

        return isAdmin;
    }

    @GetMapping("/{hubId}/exists")
    public boolean checkHubExists(
            @PathVariable("hubId") String hubId
    ) {
        log.info("Validating existence of hub '{}'", hubId);

        // 허브가 존재하는지 확인 (예외 발생 시 false 반환)
        return hubRepository.findById(hubId).isPresent();
    }
}
