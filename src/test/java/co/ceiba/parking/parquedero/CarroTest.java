package co.ceiba.parking.parquedero;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

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
import co.ceiba.parking.exception.ParqueaderoException;
import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.repository.CarroJpaRepository;
import co.ceiba.parking.testdatabuilder.CarroTestDataBuilder;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CarroTest {

	CarroTestDataBuilder ctb = new CarroTestDataBuilder();
	Carro c;
	
	@InjectMocks
	@Autowired	
	CarroDriver cd;// = new CarroDriver();
	
	@Mock
	private CarroJpaRepository repositorio;
	
	
	@Before
	public void setUp(){
				
	}
	
	@Test
	public void IngresoNoValidoPlacaIniciaPorLetraALosDomingosYLunesTest(){
		//Arrange
		CarroDriver cdr = new CarroDriver();
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		c = ctb.withPlaca("ABC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		//Act
		try {
			cdr.save(c);
			Assert.fail();
		} catch (ParqueaderoException e) {
			Assert.assertEquals("La placa ingresada no es valida", e.getMessage());
		}
		//Assert
		
	}
	
	
	@Test
	public void IngresoNoValidoPorCuposNoDisponibles(){
		//Arrange
		Mockito.when(repositorio.totalParqueados()).thenReturn(20);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		c = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		//Act
		try {
			cd.save(c);
			Assert.fail();
		} catch (ParqueaderoException e) {
		//Assert
			Assert.assertEquals("No hay espacio disponible para el Carro", e.getMessage());
		}		
	}
	
	
	@Test
	public void IngresoValido(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		c = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		//Act
		try {
			cd.save(c);
		} catch (ParqueaderoException e) {
			
		}		
	}
	
	
	@Test
	public void ErrorModificaionIngresoPlacaInexistente(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		c = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		Mockito.when(repositorio.findOne(Mockito.anyString())).thenReturn(null);
		//Act
		try {
			cd.update("ABC123",c);
			fail();
		} catch (ParqueaderoException e) {
			Assert.assertEquals("Existen datos inconsistentes en el registro a modificar", e.getMessage());
		}		
	}
	
	
	@Test
	public void BuscarTodosLosCarro(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		List<Carro> lc = new ArrayList<Carro>();
		List<Carro> esperado;
		
		cal.set(2018, 0, 28);
		c = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		
		lc.add(c);
		
		Mockito.when(repositorio.findAll()).thenReturn(lc);
		//Act
		esperado = cd.findAll();
		//Assert
		Assert.assertEquals(esperado, lc);
		
	}
	
	@Test
	public void BuscarCarroIdividual(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		Carro esperado;
		
		cal.set(2018, 0, 28);
		c = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		
		Mockito.when(repositorio.findOne(Mockito.anyString())).thenReturn(c);
		//Act
		esperado = cd.findOne("XBC123");
		//Assert
		Assert.assertEquals(esperado, c);
		
	}
}
