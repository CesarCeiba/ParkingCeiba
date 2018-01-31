package co.ceiba.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import co.ceiba.parking.crypto.Cryptografy;

@EnableJpaAuditing
@SpringBootApplication
public class main {
	
	public static void main(String[] args) {
		SpringApplication.run(main.class, args);
	}
}
