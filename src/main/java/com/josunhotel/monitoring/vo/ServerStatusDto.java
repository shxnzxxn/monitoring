package com.josunhotel.monitoring.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class ServerStatusDto {
    double maxCpuUsage;
    double memoryUsage;
    LocalDateTime rebootDate;
    List<HardDiskVO> hardDiskUsageList;
}
