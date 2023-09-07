package com.sandro.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)    // application.properties에 지정한 포트로 서버를 띄워준다.
class HelloApiTest {

    TestRestTemplate rest = new TestRestTemplate();

    @Test
    void helloApi() throws Exception {
        String name = "Spring";
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, name);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getHeaders().getContentType().toString()).contains(TEXT_PLAIN_VALUE);
        assertThat(res.getBody()).isEqualTo("*Hello " + name + "*");
    }

    @Test
    void falisHelloApi() throws Exception {
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
