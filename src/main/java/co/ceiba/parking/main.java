package co.ceiba.parking;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import co.ceiba.parking.crypto.Cryptografy;
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.logica.RegistroVehiculo;

@EnableJpaAuditing
@SpringBootApplication
public class main {
	
	private static Cryptografy c = new Cryptografy();
	
	public static void main(String[] args) {
		SpringApplication.run(main.class, args);
		
		
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatHora = new SimpleDateFormat("HH:MM:SS");
		String fecha = format.format(Calendar.getInstance().getTime());
		String hora  = format.format(Calendar.getInstance().getTime());
		
		long diff = Calendar.getInstance().getTime().getTime() - Calendar.getInstance().getTime().getTime();
		
		Calendar x = Calendar.getInstance();
		System.out.println(Calendar.getInstance().getTime());
		if (x.get(Calendar.DAY_OF_WEEK) == Calendar.getInstance().FRIDAY){
			System.out.println("uuuuz");
		}
		
		/*String encriptado, desencriptado;
		
		System.out.println(Calendar.DATE);
		System.out.println(Calendar.HOUR_OF_DAY);
		System.out.println(Calendar.SHORT_FORMAT);
		
		encriptado = c.Encriptar("cesarvelasquez20180125");
		System.out.println(encriptado);
		try{
			desencriptado = c.Desencriptar(encriptado);
			System.out.println(desencriptado);
		}catch(Exception ex){
			
		}*/
		
	}
}
