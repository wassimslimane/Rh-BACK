package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Service")
public class Service {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY) 
private int id;
private String nom_service;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom_service() {
	return nom_service;
}
public void setNom_service(String nom_service) {
	this.nom_service = nom_service;
}
public Service(String nom_service) {
	this.nom_service=nom_service;
	
}
public Service() {
}
}
