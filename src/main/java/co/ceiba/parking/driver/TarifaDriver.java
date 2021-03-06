package co.ceiba.parking.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.ceiba.parking.logica.Tarifa;
import co.ceiba.parking.repository.TarifaJpaRepository;

@RestController
@RequestMapping("/api")
public class TarifaDriver {

	
	@Autowired
	private TarifaJpaRepository repositorio;
	
	
	@GetMapping("/tarifa/getAll")
	public List<Tarifa> findAll(){
		return repositorio.findAll();	
	}	
	
	@GetMapping("/tarifa/get/{id}")
	public Tarifa findOne(@PathVariable Integer id){
		return repositorio.findOne(id);	
	}
}
