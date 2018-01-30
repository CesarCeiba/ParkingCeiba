package co.ceiba.parking.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.logica.RegistroVehiculo;
import co.ceiba.parking.repository.CarroJpaRepository;
import co.ceiba.parking.repository.MotoJpaRepository;
import co.ceiba.parking.repository.VehiculoJpaRepository;

@RestController
@RequestMapping("/api")
public class RegistroVehiculoDriver {
	
	
	@Autowired
	private CarroJpaRepository repoCar;
	@Autowired
	private MotoJpaRepository motoJpaRepository;
	@Autowired
	private VehiculoJpaRepository vehiculoJpaRepository;
	
	private RegistroVehiculo reg = new RegistroVehiculo();
	
	@GetMapping ("/registro/total/{placa}")
	public double calcularTotalTarifa (@PathVariable String placa){
		Carro car = null;
		Moto moto = null;		
		
		if (repoCar.exists(placa)) {
			car = repoCar.findOne(placa);
			return reg.totalParqueo(car);
					
		} else {
			moto = motoJpaRepository.findOne(placa);
			return reg.totalParqueo(moto);
		}
	}
	
	
	@GetMapping ("/registro/parqueadero/{placa}")
	public int vehiculoEnParqueadero (@PathVariable String placa){
		return vehiculoJpaRepository.vehiculoParqueado(placa);
	}
}
