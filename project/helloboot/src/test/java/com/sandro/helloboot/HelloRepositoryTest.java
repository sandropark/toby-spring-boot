package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
class HelloRepositoryTest {
    @Autowired HelloRepository helloRepository;

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