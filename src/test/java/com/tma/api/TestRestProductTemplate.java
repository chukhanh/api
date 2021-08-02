package com.tma.api;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestProductTemplate {

    @LocalServerPort
    private  int port;

    private  String url;
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp(){
        url = String.format("http://localhost:" + port + "/api/v1/");
    }

    @Test
    public void greetingShouldReturnDefaultMessage() {
        assertThat(this.testRestTemplate.getForObject(url, String.class)).contains("Hello World!");
    }
}
