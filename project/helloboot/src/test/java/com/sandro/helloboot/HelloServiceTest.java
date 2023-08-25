package com.sandro.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void simpleHelloService() throws Exception {
        SimpleHelloService helloService = new SimpleHelloService();
        String name = "Test";
        String ret = helloService.sayHello(name);

        assertThat(ret).isEqualTo("Hello " + name);
    }

    @Test
    void helloDecorator() throws Exception {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Spring");

        assertThat(ret).isEqualTo("*Spring*");
    }

}