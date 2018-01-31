package co.ceiba.parking.parquedero;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parking.driver.UsuarioDriver;
import co.ceiba.parking.logica.Carro;
import co.ceiba.parking.logica.Usuario;
import co.ceiba.parking.repository.UsuarioJpaRepository;
import co.ceiba.parking.testdatabuilder.UsuarioTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UsuarioTest {
	
	@InjectMocks
	@Autowired
	UsuarioDriver ud;
	
	@Mock
	UsuarioJpaRepository usuarioJpaRepository;
	
	UsuarioTestDataBuilder utdb = new UsuarioTestDataBuilder();
	
	@Test
	public void BuscarTodosLosUsuarios(){
		//Arrange					
		Usuario u = utdb.build();
		
		List<Usuario> lu = new ArrayList<Usuario>();
		List<Usuario> esperado;
		
		lu.add(u);
		lu.add(u);
		
		Mockito.when(usuarioJpaRepository.findAll()).thenReturn(lu);
		//Act
		esperado = ud.findAll();
		//Assert
		Assert.assertEquals(esperado, lu);
		
	}
	
	@Test
	public void BuscarCarroIdividual(){
		//Arrange	
		Usuario u = utdb.withUsername("cesar.velasquez").build();
		Usuario esperado;
		
		Mockito.when(usuarioJpaRepository.findOne(Mockito.anyString())).thenReturn(u);
		//Act
		esperado = ud.findOne("XBC123");
		//Assert
		Assert.assertEquals(esperado, u);
		
	}
}
