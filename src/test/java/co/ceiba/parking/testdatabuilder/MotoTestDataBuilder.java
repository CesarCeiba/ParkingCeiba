package co.ceiba.parking.testdatabuilder;

import java.util.Date;

import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.logica.Tarifa;

public class MotoTestDataBuilder {
	
	TarifaTestDataBuilder tdb = new TarifaTestDataBuilder();
	
	private String placa = "XC0145";
	
	
	private Tarifa tarifa = tdb.build();
	
	
	private Date horaIngreso = new Date(234234234234L);
	
	
	private Date horaSalida = new Date(234234234234L);
	
	
	private int cilindraje = 650;
	
	
	public MotoTestDataBuilder withPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder withTarifa(Tarifa tarifa){
		this.tarifa = tarifa;
		return this;
	}
	
	public MotoTestDataBuilder withHoraIngreso(Date horaIngreso){
		this.horaIngreso = horaIngreso;
		return this;
	}
	
	public MotoTestDataBuilder withHoraSalida(Date horaSalida){
		this.horaSalida = horaSalida;
		return this;
	}
	
	public MotoTestDataBuilder withCilindraje(int cilindraje){
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Moto build(){
		return new Moto(placa, tarifa, horaIngreso, horaSalida, cilindraje);
	}
}
