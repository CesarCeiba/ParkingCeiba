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
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.repository.MotoJpaRepository;

@RestController
@RequestMapping("/api")
public class MotoDriver {
	
	
	@Autowired
	private MotoJpaRepository repositorio;
	
	
	public void setRepositorio(MotoJpaRepository repositorio) {
		this.repositorio = repositorio;
	}


	@GetMapping("/regmoto/getAll")
	public List<Moto> findAll(){
		return repositorio.findAll();	
	}
	
	
	@GetMapping("/regmoto/get/{placa}")
	public Moto findOne(@PathVariable String placa){
		return repositorio.findOne(placa);	
	}
	
	
	@PutMapping("/regmoto/update/{placa}")
	public void update (@PathVariable String placa, @RequestBody Moto m) throws ParqueaderoException {
		
		Moto moto = repositorio.findOne(placa);
		if (moto == null){
			throw new ParqueaderoException("Existen datos inconsistentes en el registro a modificar");
		}
		
		moto.setPlaca(m.getPlaca());
		moto.setTarifa(m.getTarifa());
		moto.setHoraIngreso(m.getHoraIngreso());
		moto.setHoraSalida(m.getHoraSalida());
		moto.setCilindraje(m.getCilindraje());
		repositorio.save(moto);
	}
	
	@PostMapping("/regmoto/insert")
	public void save(@Valid @RequestBody Moto m) throws ParqueaderoException {
		
		if (!m.esPlacaValida()){
			throw new ParqueaderoException("La placa ingresada no es valida");
		}
		
		if (this.totalParqueados() == 10){
			throw new ParqueaderoException("No hay espacio disponible para la Moto");
		}
		
		repositorio.save(m);		
	}
	
	@GetMapping("/regmoto/parqueados")
	public int totalParqueados() {
		return repositorio.totalParqueados();
	}
}
