package co.ceiba.parking.parquedero;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import co.ceiba.parking.driver.MotoDriver;
import co.ceiba.parking.exception.ParqueaderoException;
import co.ceiba.parking.logica.Moto;
import co.ceiba.parking.repository.MotoJpaRepository;
import co.ceiba.parking.testdatabuilder.MotoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MotoTest {
	MotoTestDataBuilder ctb = new MotoTestDataBuilder();
	Moto m;
	
	@InjectMocks
	@Autowired	
	MotoDriver md;// = new CarroDriver();
	
	@Mock
	private MotoJpaRepository repositorio;
	
	
	@Before
	public void setUp(){
				
	}
	
	@Test
	public void IngresoNoValidoPlacaIniciaPorLetraALosDomingosYLunesTest(){
		//Arrange
		MotoDriver cdr = new MotoDriver();
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		m = ctb.withPlaca("ABC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		//Act
		try {
			cdr.save(m);
			Assert.fail();
		} catch (ParqueaderoException e) {
			Assert.assertEquals("La placa ingresada no es valida", e.getMessage());
		}
		//Assert
		
	}
	
	
	@Test
	public void IngresoNoValidoPorCuposNoDisponibles(){
		//Arrange
		Mockito.when(repositorio.totalParqueados()).thenReturn(10);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		m = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		//Act
		try {
			md.save(m);
			Assert.fail();
		} catch (ParqueaderoException e) {
		//Assert
			Assert.assertEquals("No hay espacio disponible para la Moto", e.getMessage());
		}		
	}
	
	
	@Test
	public void IngresoValido(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		m = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		//Act
		try {
			md.save(m);
		} catch (ParqueaderoException e) {
			
		}		
	}
	
	
	@Test
	public void ErrorModificaionIngresoPlacaInexistente(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 28);
		m = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		Mockito.when(repositorio.findOne(Mockito.anyString())).thenReturn(null);
		//Act
		try {
			md.update("ABC123",m);
			fail();
		} catch (ParqueaderoException e) {
			Assert.assertEquals("Existen datos inconsistentes en el registro a modificar", e.getMessage());
		}		
	}
	
	
	@Test
	public void BuscarTodosLosCarro(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		List<Moto> lm = new ArrayList<Moto>();
		List<Moto> esperado;
		
		cal.set(2018, 0, 28);
		m = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		
		lm.add(m);
		
		Mockito.when(repositorio.findAll()).thenReturn(lm);
		//Act
		esperado = md.findAll();
		//Assert
		Assert.assertEquals(esperado, lm);
		
	}
	
	@Test
	public void BuscarCarroIdividual(){
		//Arrange	
		Calendar cal = Calendar.getInstance();
		Moto esperado;
		
		cal.set(2018, 0, 28);
		m = ctb.withPlaca("XBC123")
			   .withHoraIngreso(cal.getTime())
			   .build();
		
		Mockito.when(repositorio.findOne(Mockito.anyString())).thenReturn(m);
		//Act
		esperado = md.findOne("XBC123");
		//Assert
		Assert.assertEquals(esperado, m);		
	}
}
