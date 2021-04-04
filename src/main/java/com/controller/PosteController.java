package com.controller;

import java.util.List;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IPosteRepository;
import com.entities.Poste;

@RestController
@RequestMapping("/api/poste")
public class PosteController {
	@Autowired
	IPosteRepository pr;
	
	@PostMapping("/add")
	public ResponseEntity<String> addposte(@RequestBody Poste poste) {
		try {
			pr.save(poste);
			return ResponseEntity.status(Response.SC_CREATED).body("poste ajouté");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("poste non ajouté");
		}
	}
	@GetMapping("/all")
	public List<Poste> getAll(){
		return (List<Poste>) this.pr.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteService(@PathVariable(value = "id") int idposte) {
		try {
			Poste existingposte = pr.findById(idposte).get();
			this.pr.delete(existingposte);
			return ResponseEntity.status(204).build();
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
		}

	}
}
