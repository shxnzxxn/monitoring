package com.josunhotel.monitoring.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.josunhotel.monitoring.Server;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


        this.hardDiskUsageList = roundPercentOfUsage(serverName, serverStatusDto.getHardDiskUsageList());
    }

    private List<HardDiskVO> roundPercentOfUsage(String serverName, List<HardDiskVO> hardDiskUsageList) {
        Server server = Server.valueOf(serverName);
        List<HardDiskVO> result = new ArrayList<>();

        for (HardDiskVO hardDiskVO : hardDiskUsageList) {
            double percentOfUsage = hardDiskVO.getPercentOfUsage();

            // 소수 둘째 자리까지 포맷
            String formatted = String.format("%.2f", percentOfUsage);
            hardDiskVO.setPercentOfUsage(Double.parseDouble(formatted));

            // GiB와 TiB 등 단위 표기를 변경
            replaceUnit(hardDiskVO);

            // 사용하지 않는 디스크는 삭제
            if(Server.isUsedDisk(server, hardDiskVO)){
                result.add(hardDiskVO);
            }
        }

        return result;
    }

    private void replaceUnit(HardDiskVO hardDiskVO) {
        String usage = hardDiskVO.getUsage();
        String totalSpace = hardDiskVO.getTotalSpace();

        String replaceUsage = usage.replace("GiB", "GB").replace("TiB", "TB");
        String replaceTotalUsage = totalSpace.replace("GiB", "GB").replace("TiB", "TB");

        hardDiskVO.setUsage(replaceUsage);
        hardDiskVO.setTotalSpace(replaceTotalUsage);
    }

    @Override
    public String toString() {
        return "ServerStatusVO{" +
                "serverName='" + serverName + '\'' +
                ", maxCpuUsage=" + maxCpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", rebootDate=" + rebootDate +
                ", hardDiskUsageList=" + hardDiskUsageList +
                '}';
    }
}
