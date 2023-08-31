package com.sandro.config.autoconfig;

import com.sandro.config.ConditionalMyOnClass;
import com.sandro.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@MyAutoConfiguration
public class TomcatWebServerConfig {

    @ConditionalOnMissingBean  // 해당 타입의 빈이 등록되어 있는 경우, 아래 빈 메서드는 실행되지 않는다.
    @Bean(name = "tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory(Environment env) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setContextPath(env.getProperty("contextPath"));
        return serverFactory;
    }
}
