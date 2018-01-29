package co.ceiba.parking.logica;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Moto extends Vehiculo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Column (nullable = true)
	private int cilindraje;
	
	
	public Moto(){
		super();
	}

	
	public Moto(String placa, Tarifa tarifa, Date horaIngreso, Date horaSalida, int cilindraje){
		this.setPlaca(placa);
		this.setTarifa(tarifa);
		this.setHoraIngreso(horaIngreso);
		this.setHoraSalida(horaSalida);
		this.cilindraje = cilindraje;
	}
	
	
	public int getCilindraje() {
		return cilindraje;
	}

	
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}
