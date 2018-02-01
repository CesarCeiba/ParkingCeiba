package co.ceiba.parking.logica;

import co.ceiba.parking.exception.ParqueaderoException;

public interface IUsuario {
	
	public String login(Usuario user) throws ParqueaderoException;
	
	
	public boolean logout();
	
}
