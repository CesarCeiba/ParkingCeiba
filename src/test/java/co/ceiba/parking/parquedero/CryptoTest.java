package co.ceiba.parking.parquedero;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parking.crypto.Cryptografy;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CryptoTest {
	
	@Test
	public void EncriptarCorrectamente(){
		//Arrange
		String cadenaEncriptada = "" ;
		String cadenaDesEncriptada = "123456789";
		//Act
		try {
			cadenaEncriptada = Cryptografy.Encriptar(cadenaDesEncriptada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
