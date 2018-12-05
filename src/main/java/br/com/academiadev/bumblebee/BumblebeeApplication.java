package br.com.academiadev.bumblebee;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BumblebeeApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(BumblebeeApplication.class, args);
	}

}
