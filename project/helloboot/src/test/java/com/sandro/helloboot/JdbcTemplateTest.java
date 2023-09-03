package com.sandro.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
class JdbcTemplateTest {
    @Autowired JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void insertAndQuery() throws Exception {
        jdbcTemplate.update("insert into hello values(?, ?)", "Sandro", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "Coco", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void insertAndQuery2() throws Exception {
        jdbcTemplate.update("insert into hello values(?, ?)", "Sandro", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "Coco", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

}
