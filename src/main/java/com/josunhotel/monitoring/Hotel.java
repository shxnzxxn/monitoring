package com.josunhotel.monitoring;

import lombok.Getter;

@Getter
public enum Hotel {
    WJS("wjs"),
    WJB("wjb"),
    JPG("jpg"),
    GVP("gvp"),
    FPS("fps"),
    FPM("fpm"),
    GJB("gjb"),
    GJJ("gjj"),
    LES("les");

    private final String dbName;

    Hotel(String dbName) {
        this.dbName = dbName;
    }
}
