package dev.szymon.tokenapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TokenappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenappApplication.class, args);
	}

}
