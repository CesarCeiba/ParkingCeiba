package co.ceiba.parking;

import java.text.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import co.ceiba.parking.exception.ParqueaderoException;

@EnableJpaAuditing
@SpringBootApplication
public class Main {
	
	public static void main(String[] args) throws ParqueaderoException, ParseException{
		SpringApplication.run(Main.class, args);
	}
}
