package co.ceiba.parking.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.ceiba.parking.logica.Parqueadero;
import co.ceiba.parking.repository.ParqueaderoJpaRepository;

@RestController
@RequestMapping("/api")
public class ParqueaderoDriver {
	
	
	@Autowired
	private ParqueaderoJpaRepository repositorio;
	
	
	@GetMapping("/parqueadero/get")
	public List<Parqueadero> findAll(){
		return repositorio.findAll();
	}
	
	
	@GetMapping("/parqueadero/get2")
	public String buscar(){
		return "si sirvo";
	}
}
