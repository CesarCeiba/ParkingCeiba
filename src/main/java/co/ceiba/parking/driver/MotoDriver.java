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
	public Moto update (@PathVariable String placa, @RequestBody Moto m){
		
		Moto moto = repositorio.findOne(placa);
		if (moto == null){
			return null;
		}
		
		moto.setPlaca(m.getPlaca());
		moto.setTarifa(m.getTarifa());
		moto.setHoraIngreso(m.getHoraIngreso());
		moto.setHoraSalida(m.getHoraSalida());
		moto.setCilindraje(m.getCilindraje());
		repositorio.save(moto);
		
		return moto;
	}
	
	@PostMapping("/regmoto/insert")
	public Moto save(@Valid @RequestBody Moto m){
		return repositorio.save(m);
	}
	
	@GetMapping("/regmoto/parqueados")
	public int totalParqueados() {
		return repositorio.totalParqueados();
	}
}
