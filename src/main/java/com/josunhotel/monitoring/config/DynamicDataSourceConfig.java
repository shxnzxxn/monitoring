package com.josunhotel.monitoring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    // Oracle WJS 데이터소스 설정 (기본 DB)
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.wjs")
    public DataSource oracleWjsDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle WJB 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.wjb")
    public DataSource oracleWjbDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle JPG 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.jpg")
    public DataSource oracleJpgDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle GVP 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.gvp")
    public DataSource oracleGvpDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle FPS 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.fps")
    public DataSource oracleFpsDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle FPM 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.fpm")
    public DataSource oracleFpmDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle GJB 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.gjb")
    public DataSource oracleGjbDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // Oracle GJJ 데이터소스 설정
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.gjj")
    public DataSource oracleGjjDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    // DynamicDataSource 설정
    @Bean
    public DynamicDataSource dynamicDataSource(DataSource oracleWjsDataSource,
                                               DataSource oracleWjbDataSource,
                                               DataSource oracleJpgDataSource,
                                               DataSource oracleGvpDataSource,
                                               DataSource oracleFpsDataSource,
                                               DataSource oracleFpmDataSource,
                                               DataSource oracleGjbDataSource,
                                               DataSource oracleGjjDataSource) {
        // 데이터소스를 Map으로 설정
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("wjs", oracleWjsDataSource);  // 기본 데이터소스로 설정
        targetDataSources.put("wjb", oracleWjbDataSource);
        targetDataSources.put("jpg", oracleJpgDataSource);
        targetDataSources.put("gvp", oracleGvpDataSource);
        targetDataSources.put("fps", oracleFpsDataSource);
        targetDataSources.put("fpm", oracleFpmDataSource);
        targetDataSources.put("gjb", oracleGjbDataSource);
        targetDataSources.put("gjj", oracleGjjDataSource);

        // DynamicDataSource 설정
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(oracleWjsDataSource); // oracleWjs를 기본 데이터소스로 설정
        dynamicDataSource.setTargetDataSources(targetDataSources); // 동적으로 사용할 데이터소스 설정

        return dynamicDataSource;
    }
}
