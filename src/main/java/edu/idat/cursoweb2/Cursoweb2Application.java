package edu.idat.cursoweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class Cursoweb2Application {

    @Bean
    public RestTemplate restTemplate() {
	    return new RestTemplate();
    }	

	@RequestMapping("/")
    String home() {
        return "Aplicación web que accede al API REST";
    }

	public static void main(String[] args) {
		SpringApplication.run(Cursoweb2Application.class, args);
	}

}
