package com.sandro.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloApp {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = registerBeans();
        new TomcatServletWebServerFactory()
                .getWebServer(servletContext -> servletContext
                        .addServlet("frontcontroller", getHttpServlet(applicationContext))
                        .addMapping("/*")
                ).start();
    }

    private static GenericApplicationContext registerBeans() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();   // register 후 refresh하면 빈이 생성된다.
        return applicationContext;
    }

    private static HttpServlet getHttpServlet(GenericApplicationContext applicationContext) {
        return new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                // 인증, 보안, 다국어, 공통 기능
                if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                    String name = req.getParameter("name");
                    String ret = applicationContext.getBean(HelloController.class).hello(name);

                    resp.getWriter().println(ret);
                    resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                } else {
                    resp.setStatus(HttpStatus.NOT_FOUND.value());
                }
            }
        };
    }
}
