package com.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="Admins")
@DiscriminatorValue("4")
public class Admin extends User{
	
	public Admin() {
		super();
	}
	public Admin(int idUser, String firstName, String lastName, String cin, String email, String pwd) {
		super(idUser, firstName, lastName, cin, email, pwd);
		
	}

	
	
}
