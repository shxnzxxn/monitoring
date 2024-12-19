package com.josunhotel.monitoring.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ServerStatusVO {
    private String serverName; // 서버 이름
    double maxCpuUsage;
    double memoryUsage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime rebootDate;
    List<HardDiskVO> hardDiskUsageList;

    public ServerStatusVO(String serverName, ServerStatusDto serverStatusDto) {
        this.serverName = serverName;
        this.maxCpuUsage = serverStatusDto.getMaxCpuUsage();
        this.memoryUsage = serverStatusDto.getMemoryUsage();
        this.rebootDate = serverStatusDto.getRebootDate();

//        this.hardDiskUsageList = checkHardDiskList(serverStatusDto.getHardDiskUsageList());


        this.hardDiskUsageList = roundPercentOfUsage(serverStatusDto.getHardDiskUsageList());
    }

    private List<HardDiskVO> roundPercentOfUsage(List<HardDiskVO> hardDiskUsageList) {
        for (HardDiskVO hardDiskVO : hardDiskUsageList) {
            double percentOfUsage = hardDiskVO.getPercentOfUsage();

            // 소수 둘째 자리까지 포맷
            String formatted = String.format("%.2f", percentOfUsage);
            hardDiskVO.setPercentOfUsage(Double.parseDouble(formatted));
        }

        return hardDiskUsageList;
    }

//    // C:나 D:드라이브만 취급
//    private List<HardDiskVO> checkHardDiskList(List<HardDiskVO> hardDiskUsageList) {
//        return hardDiskUsageList.stream()
//                .filter(hardDiskVO -> (
//                            hardDiskVO.getName().contains("C:") || hardDiskVO.getName().contains("D:")))
//                .toList();
//    }
}
