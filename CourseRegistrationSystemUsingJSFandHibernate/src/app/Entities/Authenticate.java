package app.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authenticate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Auth_Id")
	private int auth_Id;
	@Column(name="UserName")
	private String userName;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Role")
	private String role;
	
	
	
	public int getAuth_Id() {
		return auth_Id;
	}
	public void setAuth_Id(int auth_Id) {
		this.auth_Id = auth_Id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
