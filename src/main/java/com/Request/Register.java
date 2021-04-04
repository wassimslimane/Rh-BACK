package com.Request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Register implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size( max = 60)
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6 , max = 40)
	private String password;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Register(@NotBlank @Size(max = 60) @Email String email, @NotBlank @Size(min = 6, max = 40) String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Register() {
		
	}
	
		
	
	
	
}
