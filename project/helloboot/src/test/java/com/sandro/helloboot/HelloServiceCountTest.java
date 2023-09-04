package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
class HelloServiceCountTest {

    @Autowired HelloService helloService;
    @Autowired HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount() throws Exception {
        String name = "Sandro";

        helloService.sayHello(name);
        assertThat(helloRepository.countOf(name)).isOne();

        helloService.sayHello(name);
        assertThat(helloRepository.countOf(name)).isEqualTo(2);
    }

}