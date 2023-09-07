package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloServiceCountTest {

    @Autowired HelloService helloService;
    @Autowired HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount() throws Exception {
        helloService.sayHello("Sandro");
        assertThat(helloRepository.countOf("Sandro")).isOne();

        helloService.sayHello("Sandro");
        assertThat(helloRepository.countOf("Sandro")).isEqualTo(2);
    }

}