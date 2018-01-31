package co.ceiba.parking.logica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario implements Serializable {	

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column (nullable = false, updatable = false, length = 50)
	private String username;
	
	
	@Column (nullable = false, length = 50)
	private String password;
	
	
	@Column (nullable = false, updatable = false)
	private String token;
	
	
	public Usuario(){
		super();
	}

	
	
	
	
	public Usuario(String username, String password, String token) {
		this.username = username;
		this.password = password;
		this.token = token;
	}





	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	
}
