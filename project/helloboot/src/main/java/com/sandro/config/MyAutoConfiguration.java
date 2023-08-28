package com.sandro.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false) // @Bean 이 붙은 메서드를 호출하는 경우 프록시가 반환되지 않고 new 로 객체를 생성해서 반환한다.
public @interface MyAutoConfiguration {
}
