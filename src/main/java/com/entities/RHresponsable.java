package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity(name="RHresponsable")
@DiscriminatorValue("1")
public class RHresponsable extends User {

	private String img; //image d'identité
	private String adresse;


	
	@JsonIgnoreProperties("RHresponsable")


	


	
	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	



	public RHresponsable(int idUser , String firstName, String lastName, String cin, String email, String pwd, String img, String adresse) {
		super(idUser, firstName, lastName, cin, email, pwd);
		this.img = img;
		this.adresse = adresse;
	}



	public RHresponsable() {
		super();
	}
	
	
}
