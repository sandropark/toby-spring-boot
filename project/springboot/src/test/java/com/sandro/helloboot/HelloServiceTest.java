package com.sandro.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    private static final HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }
        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    void simpleHelloService() throws Exception {
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);

        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() throws Exception {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Spring");

        assertThat(ret).isEqualTo("*Spring*");
    }

}