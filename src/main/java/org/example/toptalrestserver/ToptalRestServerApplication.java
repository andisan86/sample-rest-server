package org.example.toptalrestserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToptalRestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToptalRestServerApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(LocationRepository repository) {
		return args -> {
			repository.save(new Location(-6.200000, 106.816666, "Jakarta"));
			repository.save(new Location(-31.953512, 115.857048, "Perth"));
			repository.save(new Location(52.520008, 13.404954, "Berlin"));
		};
	}
}
