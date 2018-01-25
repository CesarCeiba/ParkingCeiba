package co.ceiba.parking.logica;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.repository.CarroJpaRepository;
import co.ceiba.parking.repository.MotoJpaRepository;

@RestController
@RequestMapping("/api")
public class RegistroVehiculo {
	
	@Autowired
	private CarroJpaRepository repoCar;
	private MotoJpaRepository repoMoto;
	
	@GetMapping ("/registro/total/{placa}")
	public String calcularTotalTarifa (@PathVariable String placa){
		if (repoCar.exists(placa)) {
		
			Carro car;
			car = repoCar.findOne(placa);
			
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
			long diff = car.getHoraSalida().getTime() - car.getHoraIngreso().getTime();
			
			System.out.println(car.getHoraSalida().getTime());
			System.out.println(car.getHoraIngreso().getTime());
			
			return car.getHoraSalida().toString()+"     " +car.getHoraIngreso().toString(); //diff / (60*1000) % 60;
		}
		
		return ""; //0L
	}
	
}
