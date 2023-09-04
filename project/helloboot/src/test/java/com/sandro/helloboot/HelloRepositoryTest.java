package com.sandro.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed() throws Exception {
        assertThat(helloRepository.findHello("Sandro")).isNull();
    }

    @Test
    void increaseCount() throws Exception {
        String name = "Sandro";

        assertThat(helloRepository.countOf(name)).isZero();

        helloRepository.increaseCount(name);
        assertThat(helloRepository.countOf(name)).isOne();

        helloRepository.increaseCount(name);
        assertThat(helloRepository.countOf(name)).isEqualTo(2);
    }

}