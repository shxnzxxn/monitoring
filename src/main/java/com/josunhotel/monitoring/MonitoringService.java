package com.josunhotel.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josunhotel.monitoring.config.DatabaseContextHolder;
import com.josunhotel.monitoring.mapper.MonitoringMapper;
import com.josunhotel.monitoring.vo.ServerStatusDto;
import com.josunhotel.monitoring.vo.ServerStatusVO;
import com.josunhotel.monitoring.vo.TableSpaceDto;
import com.josunhotel.monitoring.vo.TableSpacesVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoringService {

    private final MonitoringMapper mapper;
    private final ObjectMapper objectMapper;
    private static final Integer PORT = 10140;
    private static final String END_POINT = "/api/system-status";

    public List<TableSpacesVO> getTableSpacesUsage() {
        // 멀티쓰레드 환경에서 쓰레드에 안전한 구현체를 사용
        List<TableSpacesVO> res = new CopyOnWriteArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Server.values().length);

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Server server : Server.values()) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    List<TableSpaceDto> tableSpaceUsageByServer = getTableSpaceUsageByServer(server);
                    res.add(new TableSpacesVO(server.name(), tableSpaceUsageByServer));
                } catch (Exception e) {
                    log.error("{} Table Space 요청 중 오류 발생: {}", server, e.getMessage());
                }
            }, executor);
            futures.add(future);
        }

        // 모든 작업이 완료될 때까지 기다림
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 스레드 풀 종료
        executor.shutdown();

        return res;
    }

    private List<TableSpaceDto> getTableSpaceUsageByServer(Server server) {
        DatabaseContextHolder.setCurrentDatabase(server.getHotel().getDbName());
        return mapper.getTableSpaceUsage();
    }

    public List<ServerStatusVO> getServersStatus() {
        // 멀티쓰레드 환경에서 쓰레드에 안전한 구현체를 사용
        List<ServerStatusVO> res = new CopyOnWriteArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Server.values().length);

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Server server : Server.values()) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    ServerStatusDto serverStatusDto = sendApi(server);
                    res.add(new ServerStatusVO(server.name(), serverStatusDto));
                } catch (IOException e) {
                    log.error("{} 서버 상태 요청 중 오류 발생: {}", server, e.getMessage());
                }
            }, executor);
            futures.add(future);
        }

        // 모든 작업이 완료될 때까지 기다림
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 스레드 풀 종료
        executor.shutdown();


        return res;
    }

    private ServerStatusDto sendApi(Server server) throws IOException {
        URL url = new URL(getApiUrl(server));

        // HttpURLConnection 생성 및 설정
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        log.info(">>>>> {}의 서버 상태를 요청합니다. <<<<<", server);
        connection.setRequestMethod("GET");

        // 응답 상태 코드 확인
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            log.error(">>>> {}에 API 요청 중 문제가 발생했습니다. <<<<<", server);
            throw new RuntimeException("Failed to connect to the server: " + responseCode);
        }

        log.info(">>>> {}의 서버 상태 요청 완료 <<<<<", server);

        // 응답 읽기
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line).append("\n");
        }
        reader.close();

        // 응답 파싱
        return objectMapper.readValue(responseBody.toString(), ServerStatusDto.class);
    }

    private String getApiUrl(Server server) {
        return "http://" + server.getIp() + ":" + PORT + END_POINT;
    }
}
