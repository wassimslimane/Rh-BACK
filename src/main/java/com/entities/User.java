package com.entities;

import javax.persistence.*;
import java.util.List;


@Entity(name="users")
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="user_type" , discriminatorType = DiscriminatorType.INTEGER)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 	
	private int idUser;
	
	@Column(name = "user_type", insertable = false, updatable = false)
    private int user_type;
	
	//@Column(name ="Prenom")
	private String firstName;
	//@Column(name ="Nom")
	private String lastName;
	//@Column(name ="CIN")
	private String cin;
	//@Column(name ="Email")
	private String email;
	//@Column(name ="Mdp")
	private String pwd;

	private boolean isValid;

	public int getId() {
		return idUser;
	}
	public void setId(int id) {
		this.idUser = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public User(int idUser,String firstName, String lastName, String cin, String email, String pwd) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.email = email;
		this.pwd = pwd;
		this.isValid = false;
	}
	public User() {
		super();
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	
	
}
