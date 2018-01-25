package co.ceiba.parking.logica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario implements IUsuario, Serializable {	


	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column (nullable = false, updatable = false, length = 50)
	private String username;
	
	
	@Column (nullable = false, length = 50)
	private String password;
	
	
	public Usuario(){
		super();
	}
	
	
	@Override
	public boolean login() {
		return true;
	}

	
	@Override
	public boolean logout() {
		return false;
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
}
