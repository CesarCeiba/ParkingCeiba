package co.ceiba.parking.logica;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Carro extends Vehiculo{

	private static final long serialVersionUID = 1L;

	public Carro(){
		super();
	}
	
	public Carro(String placa, Tarifa tarifa, Date horaIngreso, Date horaSalida){
		this.setPlaca(placa);
		this.setTarifa(tarifa);
		this.setHoraIngreso(horaIngreso);
		this.setHoraSalida(horaSalida);
	}
	
}
