package com.sandro.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping("hello")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @ResponseBody
    @GetMapping
    public String hello(String name) {
        // 컨트롤러는 요청 파라미터를 검증하는 책임을 갖고 있다. 따라서 name이 null이 아닌지 확인한다.
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
