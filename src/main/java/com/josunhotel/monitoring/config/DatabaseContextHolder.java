package com.josunhotel.monitoring.config;

public class DatabaseContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentDatabase(String databaseKey) {
        clear();
        contextHolder.set(databaseKey);
    }

    public static String getCurrentDatabase() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
