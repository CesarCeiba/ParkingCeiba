package co.ceiba.parking.driver;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
	
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
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
	

	@PutMapping(value = "/usuario/update")
	public void update (@PathVariable String username, @Valid @RequestBody Usuario u) throws ParqueaderoException{
		Usuario user = repositorio.findOne(username);
		if (user == null){
			throw new ParqueaderoException("Existen datos inconsistentes en el registro a modificar");
		}
		
		user.setPassword(u.getPassword());
		user.setToken(u.getToken());
		
		repositorio.save(user);
	}

	@PostMapping("/usuario/login")
	@Override
	public String login(@Valid @RequestBody Usuario user) throws ParqueaderoException {
		
			
		Usuario u;
		u = repositorio.findOne(user.getUsername());
		
				
		if (u == null){
			return "";
		}
		
		
		if (u.getPassword().equals(user.getPassword())){			
			
			String token = "";
			
			try {
				
				token = Cryptografy.encriptar(user.getUsername()+":::"+u.getPassword()+":::"+dateFormat.format(Calendar.getInstance().getTime()));
				
			} catch (Exception e) {
				
				throw new ParqueaderoException("Ha ocurrido un error en el Login");
				
			}
			
			this.update(u.getUsername(), u);
						
			return token;
		}
//		
		return "";
	}
	
	
	public String validarToken(String token, Calendar cal) throws ParqueaderoException, ParseException{		
		Date fechaToken;	
		String usuario;
		String password;
		SimpleDateFormat format = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
		long tiempoTranscurrido;
		String tokenDesencriptado = Cryptografy.desencriptar(token);
		String[] arr = tokenDesencriptado.split(":::");
		fechaToken = format.parse(arr[2].toString());
		
		
		tiempoTranscurrido = (cal.getTime().getTime() - fechaToken.getTime())/60000;
		
		
		if (tiempoTranscurrido >= 5){
			
			return "";
			
		}else{
			
			usuario = arr[0];
			password = arr[1];
			return Cryptografy.encriptar(usuario+":::"+password+":::"+dateFormat.format(cal.getTime()));
			
		}
	}

	

	@Override
	public boolean logout() {
		return false;
	}
}
