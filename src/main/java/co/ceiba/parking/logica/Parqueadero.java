package co.ceiba.parking.logica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Parqueadero implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column (name = "CAPACIDAD_CARROS", nullable = false)
	private int capacidadCarros;
	
	
	@Column (name = "CAPACIDAD_MOTOS", nullable = false)
	private int capacidadMotos;
	
	
	public Parqueadero(){
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCapacidadCarros() {
		return capacidadCarros;
	}


	public void setCapacidadCarros(int capacidadCarros) {
		this.capacidadCarros = capacidadCarros;
	}


	public int getCapacidadMotos() {
		return capacidadMotos;
	}


	public void setCapacidadMotos(int capacidadMotos) {
		this.capacidadMotos = capacidadMotos;
	}


	public boolean validarDisponibilidad(){
		return false;
	}	
}
