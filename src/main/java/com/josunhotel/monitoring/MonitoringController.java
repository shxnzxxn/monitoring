package com.josunhotel.monitoring;

import com.josunhotel.monitoring.vo.ServerStatusVO;
import com.josunhotel.monitoring.vo.TableSpacesVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MonitoringController {
    private final MonitoringService monitoringService;
    private final CacheManager cacheManager;

    @GetMapping("/table-space")
    @Cacheable("table-space")
    public List<TableSpacesVO> getTableSpace(){
        return monitoringService.getTableSpacesUsage();
    }

    @Scheduled(fixedRate = 1800000) // 30분마다 실행
    @CachePut("table-space") // 기존 캐시 삭제
    public List<TableSpacesVO> updateTableSpaceCache() {
        log.info("table space 캐시 갱신을 시작합니다.");
        return monitoringService.getTableSpacesUsage();
    }

    @GetMapping("/table-space/force")
    @CachePut("table-space") // 기존 캐시 삭제
    public List<TableSpacesVO> getTableSpaceForce(){
        log.info("table space - force 시작합니다.");
        return monitoringService.getTableSpacesUsage();
    }

    @GetMapping("/server-status")
    @Cacheable("server-status")
    public List<ServerStatusVO> getServerStatus(){
        return monitoringService.getServersStatus();
    }

    @Scheduled(fixedRate = 1800000) // 30분마다 실행
    @CachePut("server-status") // 기존 캐시 삭제
    public List<ServerStatusVO> updateServerStatusCache() {
        log.info("server-status 캐시 갱신을 시작합니다.");
        return monitoringService.getServersStatus(); // 실제 메서드 호출로 캐시 갱신
    }

    @GetMapping("/server-status/force")
    @CachePut("server-status") // 기존 캐시 삭제
    public List<ServerStatusVO> getServerStatusForce(){
        log.info("server status - force 시작합니다.");
        return monitoringService.getServersStatus();
    }
}
