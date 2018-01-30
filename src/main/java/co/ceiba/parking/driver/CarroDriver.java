package co.ceiba.parking.driver;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	public CarroDriver(){
		
	}
	
	@GetMapping("/regcarro/getAll")
	public List<Carro> findAll(){
		return repositorio.findAll();	
	}
	
	
	@GetMapping("/regcarro/get/{placa}")
	public Carro findOne(@PathVariable String placa){
		return repositorio.findOne(placa);	
	}
	
	
	@PutMapping("/regcarro/update/{placa}")
	public Carro update (@PathVariable String placa, @RequestBody Carro c){
		
		Carro car = repositorio.findOne(placa);
		if (car == null){
			return null;
		}
		
		car.setPlaca(c.getPlaca());
		car.setTarifa(c.getTarifa());
		car.setHoraIngreso(c.getHoraIngreso());
		car.setHoraSalida(c.getHoraSalida());
		repositorio.save(car);
		
		return car;
	}
	
	@PostMapping("/regcarro/insert")
	public boolean save(@Valid @RequestBody Carro c){
		
		if (!c.esPlacaValida()){
			return false;
		}
		
		Carro r = repositorio.save(c);
		
		return r == null ? false : true;
	}
	
	@GetMapping("/regcarro/parqueados")
	public int totalParqueados() {
		return repositorio.totalParqueados();
	}
	
}
