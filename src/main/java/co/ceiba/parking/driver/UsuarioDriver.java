package co.ceiba.parking.driver;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.ceiba.parking.logica.Usuario;
import co.ceiba.parking.repository.UsuarioJpaRepository;

@RestController
@RequestMapping("/api")
public class UsuarioDriver {
	
	@Autowired
	private UsuarioJpaRepository repositorio;
	
	
	@GetMapping("/usuario/getAll")
	public List<Usuario> findAll(){
		return repositorio.findAll();	
	}
	
	
	@GetMapping("/usuario/get/{username}")
	public Usuario findOne(@PathVariable String username){
		return repositorio.findOne(username);	
	}
	
	
	@PostMapping(value = "/usuario")
	public Usuario save (@Valid @RequestBody Usuario u){
		return repositorio.save(u);
	}
}
