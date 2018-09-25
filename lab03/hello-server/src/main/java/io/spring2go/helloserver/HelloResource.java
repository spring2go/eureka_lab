package io.spring2go.helloserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello/server")
public class HelloResource {
	
	@Value("${server.port}")
	private int port;

    @GetMapping
    public String hello() {
        return "Hello World! (server port + " + port + "). ";
    }
}
