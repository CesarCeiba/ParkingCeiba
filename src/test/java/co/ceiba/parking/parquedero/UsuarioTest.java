package co.ceiba.parking.parquedero;

import static org.junit.Assert.fail;

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
import co.ceiba.parking.exception.ParqueaderoException;
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
	public void BuscarUsuarioIdividual(){
		//Arrange	
		Usuario u = utdb.withUsername("cesar.velasquez").build();
		Usuario esperado;
		
		Mockito.when(usuarioJpaRepository.findOne(Mockito.anyString())).thenReturn(u);
		//Act
		esperado = ud.findOne("XBC123");
		//Assert
		Assert.assertEquals(esperado, u);
		
	}
	
	
	@Test
	public void LoginExitoso() throws ParqueaderoException{
		//Arrange
		String tokenEsperado = "";
		Usuario u = utdb
					.withUsername("cesar.velasquez")
					.withPassword("123")
					.build();
		Mockito.when(usuarioJpaRepository.findOne(Mockito.anyString())).thenReturn(u);
		//Act
		try {
			tokenEsperado = ud.login(u.getUsername(), u.getPassword());
		} catch (ParqueaderoException e) {
			throw new ParqueaderoException("Error en el Test -> 'LoginExitoso'");
		}
		
		//Assert
		Assert.assertNotEquals(tokenEsperado, "");
	}
	
	
	@Test
	public void LoginFallido() throws ParqueaderoException{
		//Arrange
		String tokenEsperado = "";
		Usuario u = utdb
					.withUsername("cesar.velasquez")
					.withPassword("123")
					.build();
		Mockito.when(usuarioJpaRepository.findOne(Mockito.anyString())).thenReturn(u);
		//Act
		try {
			tokenEsperado = ud.login(u.getUsername(), "1234");
		} catch (ParqueaderoException e) {
			throw new ParqueaderoException("Error en el Test -> 'LoginFallido'");
		}
		
		//Assert
		Assert.assertEquals(tokenEsperado, "");
	}
	
}
