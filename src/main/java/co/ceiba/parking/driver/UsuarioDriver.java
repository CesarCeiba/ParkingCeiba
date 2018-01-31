package co.ceiba.parking.driver;

import java.util.List;

import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.ceiba.parking.crypto.Cryptografy;
import co.ceiba.parking.exception.ParqueaderoException;
import co.ceiba.parking.logica.IUsuario;
import co.ceiba.parking.logica.Usuario;
import co.ceiba.parking.repository.UsuarioJpaRepository;

@RestController
@RequestMapping("/api")
public class UsuarioDriver implements IUsuario {
	
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

	@GetMapping("/usuario/login/{username}")
	@Override
	public String login(@PathVariable String username, String password) throws ParqueaderoException {
		Usuario u;
		u = repositorio.findOne(username);
		
		if (u == null){
			return "";
		}
		
		if (u.getPassword().equals(password)){
			
			String token = "";
			
			try {
				
				token = Cryptografy.encriptar(username+password);
				
			} catch (Exception e) {
				
				throw new ParqueaderoException("Ha ocurrido un error en el Login");
				
			}
			
			u.setToken(token);
			
			repositorio.save(u);
			
			return token;
		}
		
		return "";
	}
	
	
	@Override
	public boolean logout() {
		return false;
	}
}
