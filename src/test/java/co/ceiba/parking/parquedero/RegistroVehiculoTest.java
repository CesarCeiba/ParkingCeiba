package co.ceiba.parking.parquedero;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import co.ceiba.parking.driver.CarroDriver;
import co.ceiba.parking.driver.MotoDriver;
import co.ceiba.parking.driver.RegistroVehiculoDriver;
import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.logica.Parqueadero;
import co.ceiba.parking.logica.RegistroVehiculo;
import co.ceiba.parking.logica.Vehiculo;
import co.ceiba.parking.repository.CarroJpaRepository;
import co.ceiba.parking.repository.MotoJpaRepository;
import co.ceiba.parking.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parking.testdatabuilder.MotoTestDataBuilder;
import co.ceiba.parking.testdatabuilder.ParqueaderoTestDataBuilder;

public class RegistroVehiculoTest {
	
	@InjectMocks
	@Autowired
	CarroDriver cd = new CarroDriver();
	MotoDriver md = new MotoDriver();
	
	@Mock	
	private CarroJpaRepository repositorioCarro;
	private MotoJpaRepository repositorioMoto;

	
	Calendar cal;
	private Date fechaInicialCarro;
	private Date fechaFinalCarro;
	private Date fechaInicialMoto;
	private Date fechaFinalMoto;
	private Date fechaIngresoVehiculoPlacaIniciaA;
	
	
	CarroTestDataBuilder ctb = new CarroTestDataBuilder();
	MotoTestDataBuilder mtb = new MotoTestDataBuilder();
	RegistroVehiculo registro = new RegistroVehiculo();
	ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
	Parqueadero parqueadero = new Parqueadero();
	
	@Before
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
	public void IngresoPlacaIniciaPorLetraALosDomingosYLunesTest() {
		//Arrange		
		Carro c = ctb.withPlaca("ABC123")
					 .withHoraIngreso(fechaIngresoVehiculoPlacaIniciaA)
				     .build();		
		//Act
		boolean insertado = c.esPlacaValida();
		//Assert
		Assert.assertFalse(insertado);
	}
	
	
	@Test
	public void ParqueaderoNoDisponibleParaCarros(){
		//Arrange
		parqueadero = parqueaderoTestDataBuilder.build();		
		repositorioCarro = Mockito.mock(CarroJpaRepository.class);
		Mockito.when(repositorioCarro.totalParqueados()).thenReturn(parqueadero.getCapacidadCarros());
		cd.setRepositorio(repositorioCarro);
		
		//Act
		boolean esperado = cd.totalParqueados() == 20 ? false : true;
								
		//Assert
		Assert.assertFalse(esperado);
	}
	
	@Test
	public void ParqueaderoNoDisponibleParaMotos(){
		//Arrange
		parqueadero = parqueaderoTestDataBuilder.build();
		repositorioMoto = Mockito.mock(MotoJpaRepository.class);
		Mockito.when(repositorioMoto.totalParqueados()).thenReturn(parqueadero.getCapacidadMotos());
		md.setRepositorio(repositorioMoto);
		
		//Act
		boolean esperado = md.totalParqueados() == 10 ? false : true;
								
		//Assert
		Assert.assertFalse(esperado);
	}
	
	@Test
	public void VehiculoEnParqueadero(){
		//Arrange
		Vehiculo v = ctb.withPlaca("").build();
		RegistroVehiculoDriver vd;// 
		vd = Mockito.mock(RegistroVehiculoDriver.class);
		Mockito.when(vd.vehiculoEnParqueadero(Mockito.anyString())).thenReturn(1);
		
		//Act
		boolean esperado = v.vehiculoEnParqueadero(Optional.of(vd));
								
		//Assert
		Assert.assertTrue(esperado);
	}
	
}
