package com.josunhotel.monitoring.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.josunhotel.monitoring.mapper")
public class MyBatisConfig {
    // SqlSessionFactory 설정
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);  // DynamicDataSource를 설정

        // MyBatis 설정 파일을 추가할 수 있음
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sessionFactory.setMapperLocations(resource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mybatis-config.xml");
        sessionFactory.setConfigLocation(resources[0]);

        return sessionFactory.getObject();
    }

    // MapperScannerConfigurer 설정 (Mapper 인터페이스 자동 스캔)
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.josunhotel.monitoring.mapper"); // 패키지 경로 설정
        return scannerConfigurer;
    }
}
