package com.josunhotel.monitoring;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Server {
    WJS_APP("10.192.84.6", Hotel.WJS, true),
    WJS_BK("10.192.84.15", Hotel.WJS, false),
    WJS_OWS("10.192.84.9", Hotel.WJS, false),
    WJS_IFC("10.192.84.53", Hotel.WJS, false),

    WJB_APP("10.192.85.6", Hotel.WJB, true),
    WJB_BK("10.192.85.30", Hotel.WJB, false),
    WJB_IFC("10.192.85.52", Hotel.WJB, false),

    FPS_APP("172.30.62.72", Hotel.FPS, true),
    FPS_BK("172.30.62.85", Hotel.FPS, false),
    FPS_IFC("172.30.62.74", Hotel.FPS, false),

    FPM_APP("172.30.85.20", Hotel.FPM, true),
    FPM_BK("172.30.85.16", Hotel.FPM, false),
    FPM_IFC("172.30.85.22", Hotel.FPM, false),

    JPG_APP("172.30.113.11", Hotel.JPG, true),
    JPG_BK("172.30.113.18", Hotel.JPG, false),
    JPG_IFC("172.30.113.13", Hotel.JPG, false),
    JPG_MOBILE("172.30.113.14", Hotel.JPG, false),
    JPG_EREG("172.30.113.15", Hotel.JPG, false),

    GVP_APP("172.30.89.10", Hotel.GVP, true),
    GVP_BK("172.30.89.26", Hotel.GVP, false),
    GVP_IFC("172.30.89.12", Hotel.GVP, false),

    GJB_APP_A("172.30.81.11", Hotel.GJB, false),
    GJB_APP_B("172.30.81.10", Hotel.GJB, false),
    GJB_DB("172.30.81.12", Hotel.GJB, true),
    GJB_DG("172.30.81.13", Hotel.GJB, false),
    GJB_IFC("172.30.81.17", Hotel.GJB, false),
    GJB_OWS_A("172.30.81.15", Hotel.GJB, false),
    GJB_OWS_B("172.30.81.16", Hotel.GJB, false),
    GJB_OXI("172.30.81.18", Hotel.GJB, false),

    GJJ_APP_A("172.30.93.12", Hotel.GJJ, false),
    GJJ_APP_B("172.30.93.13", Hotel.GJJ, false),
    GJJ_DB("172.30.93.10", Hotel.GJJ, true),
    GJJ_DG("172.30.93.11", Hotel.GJJ, false),
    GJJ_IFC("172.30.93.18", Hotel.GJJ, false),
    GJJ_OWS_A("172.30.93.15", Hotel.GJJ, false),
    GJJ_OWS_B("172.30.93.16", Hotel.GJJ, false),
    GJJ_OXI("172.30.93.17", Hotel.GJJ, false),
//
    LES_APP_A("172.30.77.130", Hotel.LES, false);

    private final String ip;
    private final Hotel hotel;
    private final Boolean hasDB;

    Server(String ip, Hotel hotel, Boolean hasDB) {
        this.ip = ip;
        this.hotel = hotel;
        this.hasDB = hasDB;
    }

    public static List<Server> getDBServer(){
        return Arrays.stream(Server.values()).filter(Server::getHasDB).toList();
    }
}
