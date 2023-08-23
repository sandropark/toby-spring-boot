package com.sandro.helloboot;

import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> appClass, String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                serverFactory.getWebServer(servletContext ->
                        servletContext
                                .addServlet("dispatcherServlet", this.getBean(DispatcherServlet.class))
                                .addMapping("/*")
                ).start();
            }
        };
        applicationContext.register(appClass);
        applicationContext.refresh();   // register 후 refresh하면 빈이 생성된다.
    }
}
