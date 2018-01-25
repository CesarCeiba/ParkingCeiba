package co.ceiba.parking.logica;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Carro extends Vehiculo{

	private static final long serialVersionUID = 1L;

	public Carro(){
		super();
	}

}
