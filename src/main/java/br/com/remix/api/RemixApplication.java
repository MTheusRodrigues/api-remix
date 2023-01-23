package br.com.remix.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class RemixApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemixApplication.class, args);
    }

    @GetMapping
    public String index() {
        return "Api POC Remix";
    }

}
