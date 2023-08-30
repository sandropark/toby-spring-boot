package com.sandro.config.autoconfig;

import com.sandro.config.ConditionalMyOnClass;
import com.sandro.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
@MyAutoConfiguration
public class JettyWebServerConfig {

    @ConditionalOnMissingBean
    @Bean(name = "jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
