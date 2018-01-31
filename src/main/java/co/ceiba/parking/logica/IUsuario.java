package co.ceiba.parking.logica;

import co.ceiba.parking.exception.ParqueaderoException;

public interface IUsuario {
	
	public String login(String username, String password) throws ParqueaderoException;
	
	
	public boolean logout();
	
}
