package co.ceiba.parking.parquedero;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.ceiba.parking.driver.CarroDriver;
import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.logica.RegistroVehiculo;
import co.ceiba.parking.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parking.testdatabuilder.MotoTestDataBuilder;

public class RegistroVehiculoTest {
	
	Calendar cal;
	private Date fechaInicialCarro;
	private Date fechaFinalCarro;
	private Date fechaInicialMoto;
	private Date fechaFinalMoto;
	private Date fechaIngresoVehiculoPlacaIniciaA;
	private static Mockito mockito;
	CarroDriver cd = new CarroDriver();
	CarroTestDataBuilder ctb = new CarroTestDataBuilder();
	MotoTestDataBuilder mtb = new MotoTestDataBuilder();
	RegistroVehiculo registro = new RegistroVehiculo();
	
	@Before//(value = "")
	public void setUp(){
		cal = Calendar.getInstance();
		
		cal.set(2018, 0, 29, 07, 00, 00);//2018-01-29 07:00:00
		fechaInicialCarro = cal.getTime();
		cal.set(2018, 0, 31, 10, 00, 00);//2018-01-31 07:00:00
		fechaFinalCarro = cal.getTime();
		
		cal.set(2018, 0, 29, 07, 00, 00);//2018-01-29 07:00:00
		fechaInicialMoto = cal.getTime();
		cal.set(2018, 0, 29, 17, 00, 00);//2018-01-31 07:00:00
		fechaFinalMoto = cal.getTime();
		
		cal.set(2018, 0, 28);
		fechaIngresoVehiculoPlacaIniciaA = cal.getTime();
		
		ArrayList<Carro> lc = new ArrayList<>();
		Carro c = new Carro();
		for (int i = 0; i < 19; i++) {
			lc.add(c);
		}
		
		cd = mockito.mock(CarroDriver.class);
		mockito.when(cd.findAll()).thenReturn(lc);
		//notifyPersonService = new NotifyPersonService(emailService);
		
	}
	
	
	@Test
	public void CalcularTotalParqueoCarroTest() {
		//Arrange			
		Carro c = ctb.withHoraIngreso(fechaInicialCarro)
				     .withHoraSalida(fechaFinalCarro)
				     .build();
		
		//Act		
		double total = registro.totalParqueo(c);
		//Assert
		Assert.assertEquals(19000, total, 0);
	}
	
	
	@Test
	public void CalcularTotalParqueoMotoTest() {
		//Arrange			
		Moto m = mtb.withHoraIngreso(fechaInicialMoto)
				    .withHoraSalida(fechaFinalMoto)
				    .build();
				
		//Act		
		double total = registro.totalParqueo(m);
		//Assert
		Assert.assertEquals(6000, total, 0);
	}
	
	
	@Test
	public void ValidarIngresoPlacaIniciaLetraATest() {
		//Arrange		
		Carro c = ctb.withPlaca("ABC123")
					 .withHoraIngreso(fechaIngresoVehiculoPlacaIniciaA)
				     .build();		
		//Act
		boolean insertado = c.esPlacaValida();
		//Assert
		Assert.assertFalse(insertado);
	}
	
	
	//Validar que no este parqueado y que haya cupo
	
}
