package com.sandro.helloboot;

public interface HelloService {

    String sayHello(String name);

    default int countOf(String name) {      // API를 수정하면 테스트가 깨지기 때문에 default로 변경해서 구현하지 않으면 0을 반환하게 했다.
        return 0;
    }
}
