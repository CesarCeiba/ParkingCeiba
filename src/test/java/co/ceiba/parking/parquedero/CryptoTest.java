package co.ceiba.parking.parquedero;


import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import co.ceiba.parking.crypto.Cryptografy;
import co.ceiba.parking.exception.ParqueaderoException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CryptoTest {
	
	
	private Calendar cal = Calendar.getInstance();	
	
	@Before
	public void setUp(){
		cal.set(2018, 0, 31, 07, 00, 00);
	}
	
	
	@Test
	public void EncriptarCorrectamente() throws ParqueaderoException{
		//Arrange
		String cadenaEncriptada = "" ;
		String cadenaDesEncriptada = "123456789"+":::"+cal.getTime();
		//Act
		try {
			cadenaEncriptada = Cryptografy.encriptar(cadenaDesEncriptada);
		} catch (Exception e) {
			throw new ParqueaderoException("Ha ocurrido un error al Encriptar el Token");
		}
		//Assert
		Assert.assertEquals("q9OSeJkI6NGkU54l0ioruo+Q0ZsZgryIxUQ+axCsj0b3JZoU6TYGGgJVr++M6y/9", cadenaEncriptada);
	}
	
	@Test
	public void DesencriptarCorrectamente() throws ParqueaderoException{
		//Arrange
		String cadenaEncriptada = "q9OSeJkI6NGkU54l0ioruo+Q0ZsZgryIxUQ+axCsj0b3JZoU6TYGGgJVr++M6y/9";
		String cadenaDesEncriptada = "";
		//Act
		try {
			cadenaDesEncriptada = Cryptografy.desencriptar(cadenaEncriptada);
		} catch (Exception e) {
			throw new ParqueaderoException("Ha ocurrido un error al Desencriptar el Token");
		}
		//Assert
		Assert.assertEquals("123456789:::Wed Jan 31 07:00:00 COT 2018", cadenaDesEncriptada);
	}
}
