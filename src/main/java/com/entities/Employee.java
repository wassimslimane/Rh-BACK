package com.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;



@Entity(name="Employes")
@DiscriminatorValue("2")
public class Employee extends User {

	
	private String poste;
	//@Column(name ="Date d'Embauche")
	private Date dateDembauche;
	//@Column(name ="Date de Sortie")
	private Date dateSortie;
	//@Column(name ="Date de Naissance")
	private Date dateNaissance;
	private String img;
	private String adresse;
	private String phone;
	private String service;
	private String matricule;
	
	private Integer responsable;
	
	
	
	
	

	
	
	
	
	public Integer getResponsable() {
		return responsable;
	}
	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getPoste() {
		return poste;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDateDembauche() {
		return dateDembauche;
	}
	public void setDateDembauche(Date dateDembauche) {
		this.dateDembauche = dateDembauche;
	}
	public Date getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
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

	public Employee(int idUser ,String firstName, String lastName, String cin, String email, String pwd, String poste,
			Date dateDembauche, Date dateSortie, Date dateNaissance,String img,String Adresse,String phone,String service,String matricule,Integer responsable) {
		super(idUser, firstName, lastName, cin, email, pwd);
		this.poste = poste;
		this.dateDembauche = dateDembauche;
		this.dateSortie = dateSortie;
		this.dateNaissance = dateNaissance;
		this.img=img;
		this.adresse=Adresse;
		this.phone=phone;
		this.service=service;
		this.matricule=matricule;
		this.responsable=responsable;
		
		
	}
	
	public Employee() {
		
	}
	
	public Employee(int idUser, String firstName, String lastName, String cin, String email, String pwd) {
		super(idUser, firstName, lastName, cin, email, pwd);
	}
	
	
	
}
