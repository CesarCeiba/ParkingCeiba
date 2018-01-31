package co.ceiba.parking.parquedero;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parking.crypto.Cryptografy;
import co.ceiba.parking.exception.ParqueaderoException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CryptoTest {
	
	@Test
	public void EncriptarCorrectamente() throws ParqueaderoException{
		//Arrange
		String cadenaEncriptada = "" ;
		String cadenaDesEncriptada = "123456789";
		//Act
		try {
			cadenaEncriptada = Cryptografy.encriptar(cadenaDesEncriptada);
		} catch (Exception e) {
			throw new ParqueaderoException("Ha ocurrido un error al Encriptar el Token");
		}
		//Assert
		Assert.assertEquals("q9OSeJkI6NEw57X2w7vpnQ==", cadenaEncriptada);
	}
	
	@Test
	public void DesencriptarCorrectamente() throws ParqueaderoException{
		//Arrange
		String cadenaEncriptada = "q9OSeJkI6NEw57X2w7vpnQ==";
		String cadenaDesEncriptada = "";
		//Act
		try {
			cadenaDesEncriptada = Cryptografy.desencriptar(cadenaEncriptada);
		} catch (Exception e) {
			throw new ParqueaderoException("Ha ocurrido un error al Desencriptar el Token");
		}
		//Assert
		Assert.assertEquals("123456789", cadenaDesEncriptada);
	}
}
