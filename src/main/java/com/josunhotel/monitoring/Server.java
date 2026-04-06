package com.josunhotel.monitoring;

import com.josunhotel.monitoring.vo.HardDiskVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Server {
    WJS_APP("10.192.84.6", Hotel.WJS, true, "D:"),
    WJS_BK("10.192.84.15", Hotel.WJS, false, "D:"),
    WJS_OWS("10.192.84.9", Hotel.WJS, false, "D:"),
    WJS_IFC("10.192.84.53", Hotel.WJS, false, "C:"),
    WJS_TRAINING("10.192.84.14", Hotel.WJS_TRAINING, true, "D:"),

    WJB_APP("10.192.85.6", Hotel.WJB, true, "D:"),
    WJB_BK("10.192.85.30", Hotel.WJB, false, "D:"),
    WJB_OWS("10.192.85.9", Hotel.WJB, false, "D:"),
    WJB_IFC("10.192.85.52", Hotel.WJB, false, "C:"),

    FPS_APP("172.30.62.72", Hotel.FPS, true, "D:"),
    FPS_BK("172.30.62.85", Hotel.FPS, false, "D:"),
    FPS_OWS("172.30.62.79", Hotel.FPS, false, "D:"),
    FPS_IFC("172.30.62.74", Hotel.FPS, false, "C:"),

    FPM_APP("172.30.85.20", Hotel.FPM, true, "D:"),
    FPM_BK("172.30.85.16", Hotel.FPM, false, "D:"),
    FPM_OWS("172.30.85.17", Hotel.FPM, false, "D:"),
    FPM_IFC("172.30.85.22", Hotel.FPM, false, "C:"),

    JPG_APP("172.30.113.11", Hotel.JPG, true, "D:"),
    JPG_BK("172.30.113.18", Hotel.JPG, false, "D:"),
    JPG_OWS("172.30.113.16", Hotel.JPG, false, "D:"),
    JPG_IFC("172.30.113.13", Hotel.JPG, false, "C:"),

    GVP_APP("172.30.89.10", Hotel.GVP, true, "D:"),
    GVP_BK("172.30.89.26", Hotel.GVP, false, "D:"),
    GVP_OWS("172.30.89.23", Hotel.GVP, false, "D:"),
    GVP_IFC("172.30.89.12", Hotel.GVP, false, "C:"),

    GJB_APP_A("172.30.81.11", Hotel.GJB, false, "D:"),
    GJB_APP_B("172.30.81.10", Hotel.GJB, false, "D:"),
    GJB_DB("172.30.81.12", Hotel.GJB, true, "D:"),
    GJB_DG("172.30.81.13", Hotel.GJB, false, "D:"),
    GJB_IFC("172.30.81.17", Hotel.GJB, false, "C:"),
    GJB_OWS_A("172.30.81.15", Hotel.GJB, false, "D:"),
    GJB_OXI("172.30.81.18", Hotel.GJB, false, "D:"),

    GJJ_APP_A("172.30.93.12", Hotel.GJJ, false, "D:"),
    GJJ_APP_B("172.30.93.13", Hotel.GJJ, false, "D:"),
    GJJ_DB("172.30.93.10", Hotel.GJJ, true, "D:"),
    GJJ_DG("172.30.93.11", Hotel.GJJ, false, "D:"),
    GJJ_IFC("172.30.93.18", Hotel.GJJ, false, "C:"),
    GJJ_OWS_A("172.30.93.15", Hotel.GJJ, false, "D:"),
    GJJ_OXI("172.30.93.17", Hotel.GJJ, false, "D:"),

    LES_APP("172.30.77.145", Hotel.LES, true, "D:"),
    LES_BK("172.30.77.146", Hotel.LES, false, "D:"),
    LES_OWS("172.30.77.147", Hotel.LES, false, "D:"),
    LES_IFC("172.30.77.148", Hotel.LES, false, "C:");


    private final String ip;
    private final Hotel hotel;
    private final Boolean hasDB;
    private final String diskName;

    public static boolean isUsedDisk(Server server, HardDiskVO hardDiskVO) {
        return hardDiskVO.getName().contains(server.getDiskName());
    }
}
