package co.ceiba.parking.parquedero;

import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
import co.ceiba.parking.repository.VehiculoJpaRepository;
import co.ceiba.parking.testdatabuilder.CarroTestDataBuilder;
import co.ceiba.parking.testdatabuilder.MotoTestDataBuilder;
import co.ceiba.parking.testdatabuilder.ParqueaderoTestDataBuilder;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class RegistroVehiculoTest {
	
	@InjectMocks
	@Autowired
	CarroDriver cd;
	
	@InjectMocks
	@Autowired
	MotoDriver md;
	
	@InjectMocks
	@Autowired
	RegistroVehiculoDriver vd; 
	
	@Mock	
	private CarroJpaRepository repositorioCarro;
	
	@Mock
	private MotoJpaRepository repositorioMoto;
	
	@Mock
	private VehiculoJpaRepository vehiculoJpaRepository;

	
	Calendar cal;
	private Date fechaInicialCarro;
	private Date fechaFinalCarro;
	private Date fechaInicialMoto;
	private Date fechaFinalMoto;
	
	
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
	}
	
	
	@Test
	public void CalcularTotalParqueoCarroTest() {
		//Arrange			
		Carro c = ctb.withHoraIngreso(fechaInicialCarro)
				     .withHoraSalida(fechaFinalCarro)
				     .build();
		
		//repositorioCarro.
		
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
	public void ParqueaderoNoDisponibleParaCarros(){
		//Arrange
		parqueadero = parqueaderoTestDataBuilder.build();		
		Mockito.when(repositorioCarro.totalParqueados()).thenReturn(parqueadero.getCapacidadCarros());
		
		//Act
		boolean esperado = cd.totalParqueados() == 20 ? false : true;
								
		//Assert
		Assert.assertFalse(esperado);
	}
	
	@Test
	public void ParqueaderoNoDisponibleParaMotos(){
		//Arrange
		parqueadero = parqueaderoTestDataBuilder.build();
		Mockito.when(repositorioMoto.totalParqueados()).thenReturn(parqueadero.getCapacidadMotos());
		
		//Act
		boolean esperado = md.totalParqueados() == 10 ? false : true;
								
		//Assert
		Assert.assertFalse(esperado);
	}
	
	@Test
	public void VehiculoEnParqueadero(){
		//Arrange
		Vehiculo v = ctb.withPlaca("XYZ987").build();		
		Mockito.when(vehiculoJpaRepository.vehiculoParqueado(Mockito.anyString())).thenReturn(1);
		
		//Act
		int esperado = vd.vehiculoEnParqueadero(v.getPlaca()); //v.vehiculoEnParqueadero(Optional.of(vd));
								
		//Assert
		Assert.assertEquals(1, esperado);
	}
	
}
