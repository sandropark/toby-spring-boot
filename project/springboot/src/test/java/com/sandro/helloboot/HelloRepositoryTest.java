package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HelloRepositoryTest {
    @Autowired HelloRepository helloRepository;

    @Test
    void findHelloFailed() throws Exception {
        assertThat(helloRepository.findHello("noName")).isNull();
    }

    @Test
    void increaseCount() throws Exception {
        assertThat(helloRepository.countOf("Sandro")).isZero();

        helloRepository.increaseCount("Sandro");
        assertThat(helloRepository.countOf("Sandro")).isOne();

        helloRepository.increaseCount("Sandro");
        assertThat(helloRepository.countOf("Sandro")).isEqualTo(2);
    }

}