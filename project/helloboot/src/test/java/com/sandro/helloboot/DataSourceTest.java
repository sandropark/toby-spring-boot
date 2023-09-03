package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;

@HellobootTest
class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    void connect() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

}
