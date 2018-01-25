package co.ceiba.parking.logica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table
public class Tarifa implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column (name = "id_tarifa")
	private int id;
	
	
	@Column (name = "VALOR_HORA_CARRO", nullable = false)
	private double valorHoraCarro;
	
	
	@Column (name = "VALOR_DIA_CARRO", nullable = false)
	private double valorDiaCarro;
	
	
	@Column (name = "VALOR_HORA_MOTO", nullable = false)
	private double valorHoraMoto;
	
	
	@Column (name = "VALOR_DIA_MOTO", nullable = false)
	private double valorDiaMoto;
	
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public double getValorHoraCarro() {
		return valorHoraCarro;
	}
	
	
	public void setValorHoraCarro(double valorHoraCarro) {
		this.valorHoraCarro = valorHoraCarro;
	}
	
	
	public double getValorDiaCarro() {
		return valorDiaCarro;
	}
	
	
	public void setValorDiaCarro(double valorDiaCarro) {
		this.valorDiaCarro = valorDiaCarro;
	}
	
	
	public double getValorHoraMoto() {
		return valorHoraMoto;
	}
	
	
	public void setValorHoraMoto(double valorHoraMoto) {
		this.valorHoraMoto = valorHoraMoto;
	}
	
	
	public double getValorDiaMoto() {
		return valorDiaMoto;
	}
	
	
	public void setValorDiaMoto(double valorDiaMoto) {
		this.valorDiaMoto = valorDiaMoto;
	}
	
	

	
}
