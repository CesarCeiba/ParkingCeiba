package co.ceiba.parking.logica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.driver.TarifaDriver;
import co.ceiba.parking.repository.CarroJpaRepository;
import co.ceiba.parking.repository.MotoJpaRepository;

@RestController
@RequestMapping("/api")
public class RegistroVehiculo {
	
	@Autowired
	private CarroJpaRepository repoCar;
	@Autowired
	private MotoJpaRepository repoMoto;
	
	@GetMapping ("/registro/total/{placa}")
	public double calcularTotalTarifa (@PathVariable String placa){
		double diff;
		double horas;
		double minutos;
		double adicionales = 0;
		int dias = 0;
		boolean isCarro = false;
		
		double totalValorParqueo = 0;		
		
				
		Carro car = null;
		Moto moto = null;
		TarifaDriver td = new TarifaDriver();
		
		
		if (repoCar.exists(placa)) {
			car = repoCar.findOne(placa);
			diff = car.getHoraSalida().getTime() - car.getHoraIngreso().getTime();
					
		}else{
			moto = repoMoto.findOne(placa);
			diff = moto.getHoraSalida().getTime() - moto.getHoraIngreso().getTime();
			
			if (moto.getCilindraje() > 500){
				adicionales = 2000;
			}			
		}
		
		//Convert milliseconds to minutes
		minutos = ((diff)/60000);
		horas = Math.ceil(minutos/60);	
		
		
		
		// < 9 Hours
		if (horas < 9){
									
			if (moto != null){
				Tarifa t = td.findOne(moto.getTarifa().getId());
				totalValorParqueo = t.getValorHoraMoto()*(horas);
				
			} else{			
				Tarifa t = td.findOne(car.getTarifa().getId());
				totalValorParqueo = t.getValorHoraCarro()*(horas);			
			}
			
		// >= 9 Hours
		}else{
			int minutosRestanteCobroPorDia = 0;
			
			//convert to days
			dias = (int) (Math.round(horas)/24);			
			
			if (moto != null){
				Tarifa t = td.findOne(moto.getTarifa().getId());
				totalValorParqueo = t.getValorDiaMoto()*(diff/60);
			} else {
				Tarifa t = td.findOne(car.getTarifa().getId());
				totalValorParqueo = t.getValorDiaCarro()*(diff/60);
			}
		}
		
		
		return totalValorParqueo + adicionales;
	}
	
}
