package co.ceiba.parking.testdatabuilder;

import co.ceiba.parking.logica.Tarifa;

public class TarifaTestDataBuilder {


	private double valorHoraCarro = 1000;
	
	
	private double valorDiaCarro = 8000;
	
	
	private double valorHoraMoto = 500;
	
	
	private double valorDiaMoto = 4000;
	
	
	public TarifaTestDataBuilder withValorHoraCarro(double valorHoraCarro){
		this.valorHoraCarro = valorHoraCarro;
		return this;
	}
	
	public TarifaTestDataBuilder withValorHoraMoto(double valorHoraMoto){
		this.valorHoraMoto = valorHoraMoto;
		return this;
	}
	
	public TarifaTestDataBuilder withValorDiaCarro(double valorDiaCarro){
		this.valorDiaCarro = valorDiaCarro;
		return this;
	}
	
	public TarifaTestDataBuilder withValorDiaMoto(double valorDiaMoto){
		this.valorDiaMoto = valorDiaMoto;
		return this;
	}
	
	public Tarifa build(){
		return new Tarifa(this.valorHoraCarro, this.valorDiaCarro, this.valorHoraMoto, this.valorDiaMoto);
	}
}
