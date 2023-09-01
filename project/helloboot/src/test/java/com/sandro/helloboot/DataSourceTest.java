package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@TestPropertySource("classpath:/application.properties")
@ContextConfiguration(classes = HelloApp.class)
@ExtendWith(SpringExtension.class)
class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    void connect() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
