package co.ceiba.parking.driver;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.repository.CarroJpaRepository;

@RestController
@RequestMapping("/api")
public class CarroDriver {
	
	
	@Autowired
	private CarroJpaRepository repositorio;
	
	
	@GetMapping("/carro/getAll")
	public List<Carro> findAll(){
		return repositorio.findAll();	
	}
	
	
	@GetMapping("/carro/get/{placa}")
	public Carro findOne(@PathVariable String placa){
		return repositorio.findOne(placa);	
	}
	
	
	@PostMapping("/carro")
	public Carro save(@Valid @RequestBody Carro c){
		return repositorio.save(c);
	}
}
