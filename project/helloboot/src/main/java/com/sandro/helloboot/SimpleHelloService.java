package com.sandro.helloboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimpleHelloService implements HelloService {
    private final HelloRepository helloRepository;

    @Override
    public String sayHello(String name) {
        helloRepository.increaseCount(name);
        return "Hello " + name;
    }

    @Override
    public int countOf(String name) {
        return helloRepository.countOf(name);
    }
}
