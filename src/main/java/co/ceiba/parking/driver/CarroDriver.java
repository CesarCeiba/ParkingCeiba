package co.ceiba.parking.driver;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parking.exception.ParqueaderoException;
import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.repository.CarroJpaRepository;

@RestController
@RequestMapping("/api")
public class CarroDriver {
	
	
	@Autowired
	private CarroJpaRepository repositorio;
	

	public void setRepositorio(CarroJpaRepository repositorio) {
		this.repositorio = repositorio;
	}

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
	public void update (@PathVariable String placa, @RequestBody Carro c) throws ParqueaderoException{
		
		Carro car = repositorio.findOne(placa);
		if (car == null){
			throw new ParqueaderoException("Existen datos inconsistentes en el registro a modificar");
		}
		
		car.setPlaca(c.getPlaca());
		car.setTarifa(c.getTarifa());
		car.setHoraIngreso(c.getHoraIngreso());
		car.setHoraSalida(c.getHoraSalida());
		repositorio.save(car);
		

	}
	
	@PostMapping("/regcarro/insert")
	public void save(@Valid @RequestBody Carro c) throws ParqueaderoException {
		
		if (!c.esPlacaValida()){
			throw new ParqueaderoException("La placa ingresada no es valida");
		}
		
		if (this.totalParqueados() == 20){
			throw new ParqueaderoException("No hay espacio disponible para el Carro");
		}
		
		repositorio.save(c);
	}
	
	@GetMapping("/regcarro/parqueados")
	public int totalParqueados() {
		return repositorio.totalParqueados();
	}
	
}
