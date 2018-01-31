package co.ceiba.parking.parquedero;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.ceiba.parking.crypto.Cryptografy;

public class CryptoTest {
	
	@Test
	public void EncriptarCorrectamente(){
		//Arrange
		String cadenaEncriptada ;
		String cadenaDesEncriptada = "123456789";
		//Act
		cadenaEncriptada = Cryptografy.Encriptar(cadenaDesEncriptada);
		//Assert
		Assert.assertEquals("q9OSeJkI6NEw57X2w7vpnQ==", cadenaEncriptada);
	}
	
	@Test
	public void DesencriptarCorrectamente(){
		//Arrange
		String cadenaEncriptada = "q9OSeJkI6NEw57X2w7vpnQ==";
		String cadenaDesEncriptada = "";
		//Act
		try {
			cadenaDesEncriptada = Cryptografy.Desencriptar(cadenaEncriptada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Assert
		Assert.assertEquals("123456789", cadenaDesEncriptada);
	}
}
