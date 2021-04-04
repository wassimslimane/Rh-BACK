package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Poste")
public class Poste {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY) 
private int id;
private String nom_poste;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom_poste() {
	return nom_poste;
}
public void setNom_poste(String nom_poste) {
	this.nom_poste = nom_poste;
}
public Poste(String nom_poste) {
	this.nom_poste=nom_poste;
	
}
public Poste() {
}
}
