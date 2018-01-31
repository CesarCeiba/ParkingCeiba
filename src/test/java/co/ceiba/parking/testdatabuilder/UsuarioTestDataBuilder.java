package co.ceiba.parking.testdatabuilder;

import co.ceiba.parking.logica.Usuario;

public class UsuarioTestDataBuilder {
	
	private String username = "cesarvelasquez";
	
	private String password = "123";
	
	private String token = "q9OSeJkI6NEw57X2w7vpnQ==";
	
	public UsuarioTestDataBuilder withUsername(String username){
		this.username = username;
		return this;
	}
	
	
	public UsuarioTestDataBuilder withPassword(String password){
		this.password = password;
		return this;
	}
	
	
	public UsuarioTestDataBuilder withToken(String token){
		this.token = token;
		return this;
	}
	
	
	public Usuario build(){
		return new Usuario(this.username, this.password, this.token);
	}
}
