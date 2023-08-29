package com.sandro.config.autoconfig;

import com.sandro.config.ConditionalMyOnClass;
import com.sandro.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@MyAutoConfiguration
public class TomcatWebServerConfig {

    @Bean(name = "tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
