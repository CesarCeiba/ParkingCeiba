package co.ceiba.parking.testdatabuilder;

import co.ceiba.parking.logica.Parqueadero;

public class ParqueaderoTestDataBuilder {

	private int capacidadCarros = 20;
	
	
	private int capacidadMotos = 10;
	
	
	public ParqueaderoTestDataBuilder withCapacidadCarros(int capacidadCarros){
		this.capacidadCarros = capacidadCarros;
		return this;
	}
	
	
	public ParqueaderoTestDataBuilder withCapacidadMotos(int capacidadMotos){
		this.capacidadMotos = capacidadMotos;
		return this;
	}
	
	
	public Parqueadero build(){
		return new Parqueadero(this.capacidadCarros, this.capacidadMotos);
	}
}
