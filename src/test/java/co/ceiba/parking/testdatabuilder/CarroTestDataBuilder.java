package co.ceiba.parking.testdatabuilder;

import java.util.Date;

import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.logica.Tarifa;

public class CarroTestDataBuilder {
	
	TarifaTestDataBuilder tdb = new TarifaTestDataBuilder();
	
	private String placa = "XC0145";
	
	
	private Tarifa tarifa = tdb.build();
	
	
	private Date horaIngreso = new Date(234234234234L);
	
	
	private Date horaSalida = new Date(234234234234L);
	
	
	public CarroTestDataBuilder withPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public CarroTestDataBuilder withTarifa(Tarifa tarifa){
		this.tarifa = tarifa;
		return this;
	}
	
	public CarroTestDataBuilder withHoraIngreso(Date horaIngreso){
		this.horaIngreso = horaIngreso;
		return this;
	}
	
	public CarroTestDataBuilder withHoraSalida(Date horaSalida){
		this.horaSalida = horaSalida;
		return this;
	}
	
	public Carro build(){
		return new Carro(this.placa, this.tarifa, this.horaIngreso, this.horaSalida);
	}
}
