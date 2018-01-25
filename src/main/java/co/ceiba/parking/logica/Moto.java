package co.ceiba.parking.logica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Moto extends Vehiculo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Column
	private int cilindraje;
	
	
	public Moto(){
		super();
	}

	
	public int getCilindraje() {
		return cilindraje;
	}

	
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}
