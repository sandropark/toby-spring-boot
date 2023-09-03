package com.sandro.config.autoconfig;

import com.sandro.config.ConditionalMyOnClass;
import com.sandro.config.EnableMyConfigurationProperties;
import com.sandro.config.MyAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

@EnableTransactionManagement    // 트랜잭션 AOP를 사용하기 위해서 (@Transactional을 사용할 수 있다.)
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@MyAutoConfiguration
public class DataSourceConfig {

    @ConditionalOnMissingBean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @Bean
    DataSource hikariDataSource(MyDataSourceProperties prop) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(prop.getDriverClassName());
        dataSource.setJdbcUrl(prop.getUrl());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());
        return dataSource;
    }

    @ConditionalOnMissingBean
    @Bean
    DataSource dataSource(MyDataSourceProperties prop) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(prop.getDriverClassName()));
        dataSource.setUrl(prop.getUrl());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());

        return dataSource;
    }

    @ConditionalOnSingleCandidate(DataSource.class) // DataSource 타입 빈이 하나만 등록된 경우에 이 빈을 등록하겠다는 뜻
    @ConditionalOnMissingBean
    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @ConditionalOnSingleCandidate(DataSource.class) // DataSource 타입 빈이 하나만 등록된 경우에 이 빈을 등록하겠다는 뜻
    @ConditionalOnMissingBean
    @Bean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
