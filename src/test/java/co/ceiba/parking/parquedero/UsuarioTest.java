package co.ceiba.parking.parquedero;


import java.text.ParseException;
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
	
//	@Mock
//	Calendar cal;
	
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
			tokenEsperado = ud.login(u);
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
					.withPassword("1234")
					.build();
		Usuario u_retornado = utdb
				.withUsername("cesar.velasquez")
				.withPassword("123")
				.build();
		Mockito.when(usuarioJpaRepository.findOne(Mockito.anyString())).thenReturn(u_retornado);
		//Act
		try {
			tokenEsperado = ud.login(u);
			System.out.println("Test: "+tokenEsperado);
		} catch (ParqueaderoException e) {
			throw new ParqueaderoException("Error en el Test -> 'LoginFallido'");
		}
		
		//Assert
		Assert.assertTrue(tokenEsperado.isEmpty());
	}
	
	
	@Test
	public void validacionTokenExitosaDentroDelTiempoLimite() throws ParqueaderoException, ParseException{
		//Arrange
		String tokenEsperado = "";
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 31, 07, 04, 59);

		
		//Act
		tokenEsperado = ud.validarToken("qfnZKrgU21azN7Z8e3Kft3UzH/X2rgbhYB+9kdP8hoOEnd9R4KC8oImZNLVkqgS4", cal);


		//Assert
		Assert.assertTrue(!tokenEsperado.isEmpty());
	}
	
	
	@Test
	public void validacionTokenExitosaTiempoAgotado() throws ParqueaderoException, ParseException{
		//Arrange
		String tokenEsperado = "";
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 31, 07, 05, 59);
		
		
		//Act
		tokenEsperado = ud.validarToken("qfnZKrgU21azN7Z8e3Kft3UzH/X2rgbhYB+9kdP8hoOEnd9R4KC8oImZNLVkqgS4", cal);
		
		
		//Assert
		Assert.assertTrue(tokenEsperado.isEmpty());
	}
	
}
