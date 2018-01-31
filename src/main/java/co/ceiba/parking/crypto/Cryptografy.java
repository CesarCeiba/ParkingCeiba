package co.ceiba.parking.crypto;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import co.ceiba.parking.exception.ParqueaderoException;

public class Cryptografy {
		
	private static String codificacion = "utf-8";
	private static String algoritmoCodificacion = "MD5";
	private static String estandarEncriptado = "DESede";
	private static String secretKey = "CeibaParking";
	
	private Cryptografy(){
		
	}
	
	public static String encriptar(String texto) throws ParqueaderoException {
		
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance(algoritmoCodificacion);
            byte[] digestOfPassword = md.digest(secretKey.getBytes(codificacion));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, estandarEncriptado);
            Cipher cipher = Cipher.getInstance(estandarEncriptado);
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes(codificacion);
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        	throw new ParqueaderoException("Ha ocurrido al encriptar la clave: "+ex.getMessage());        	
        }
        return base64EncryptedString;
	}
	
	public static String desencriptar(String textoEncriptado) throws ParqueaderoException {
		 
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes(codificacion));
            MessageDigest md = MessageDigest.getInstance(algoritmoCodificacion);
            byte[] digestOfPassword = md.digest(secretKey.getBytes(codificacion));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, estandarEncriptado);
 
            Cipher decipher = Cipher.getInstance(estandarEncriptado);
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, codificacion);
 
        } catch (Exception ex) {
        	throw new ParqueaderoException ("Ha ocurrido al encriptar la clave: "+ex.getMessage());
        }
        return base64EncryptedString;
    }
}
