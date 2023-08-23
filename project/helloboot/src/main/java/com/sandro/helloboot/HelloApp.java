package com.sandro.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan
public class HelloApp {
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                serverFactory.getWebServer(servletContext ->
                        servletContext
                                .addServlet("dispatcherServlet", new DispatcherServlet(this))
                                .addMapping("/*")
                ).start();
            }
        };
        applicationContext.register(HelloApp.class);
        applicationContext.refresh();   // register 후 refresh하면 빈이 생성된다.
    }

}
