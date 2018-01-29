package co.ceiba.parking.logica;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public abstract class Vehiculo  implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	@Column (length = 10, nullable = false, updatable = false)
	private String placa;
	
	
	@Column (name = "id_registro", nullable = false, updatable = false)
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long idRegistro;
		
	
	@OneToOne
	@JoinColumn(name="id_tarifa", nullable = false)
	private Tarifa tarifa;
	
	
	@Column (nullable = false, updatable = false)
	private Date horaIngreso;
	
	
	@Column (nullable = true)
	private Date horaSalida;
	
	
	public Vehiculo(){
		
	}

	
	public String getPlaca() {
		return placa;
	}

	
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	
	public Date getHoraIngreso() {
		return horaIngreso;
	}

	
	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	
	public Date getHoraSalida() {
		return horaSalida;
	}

	
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	
	public Tarifa getTarifa() {
		return tarifa;
	}

	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
	public boolean esPlacaValida(){
		Calendar c = Calendar.getInstance();
		c.setTime(this.getHoraIngreso());
		boolean diaPermitido = true;
		diaPermitido = (c.get(Calendar.DAY_OF_WEEK) == Calendar.getInstance().SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.getInstance().MONDAY ? false : true);		

		if (this.getPlaca().toUpperCase().startsWith("A")){
			return diaPermitido;
		}else{
			return true;
		}
	}
}
