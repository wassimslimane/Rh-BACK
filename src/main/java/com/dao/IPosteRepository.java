package com.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.entities.Poste;

@Transactional

public interface IPosteRepository  extends CrudRepository<Poste, Integer>  { 



	
}
