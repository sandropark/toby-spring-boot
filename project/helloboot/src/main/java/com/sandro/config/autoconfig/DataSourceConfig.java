package com.sandro.config.autoconfig;

import com.sandro.config.ConditionalMyOnClass;
import com.sandro.config.EnableMyConfigurationProperties;
import com.sandro.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@MyAutoConfiguration
public class DataSourceConfig {
    @Bean
    DataSource dataSource(MyDataSourceProperties prop) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(prop.getDriverClassName()));
        dataSource.setUrl(prop.getUrl());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());

        return dataSource;
    }
}