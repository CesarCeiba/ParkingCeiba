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
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.repository.MotoJpaRepository;

@RestController
@RequestMapping("/api")
public class MotoDriver {
	
	
	@Autowired
	private MotoJpaRepository repositorio;
	
	
	@GetMapping("/moto/getAll")
	public List<Moto> findAll(){
		return repositorio.findAll();	
	}
	
	
	@GetMapping("/moto/get/{placa}")
	public Moto findOne(@PathVariable String placa){
		return repositorio.findOne(placa);	
	}
	
	
	@PostMapping("/moto")
	public Moto save(@Valid @RequestBody Moto m){
		return repositorio.save(m);
	}
}
