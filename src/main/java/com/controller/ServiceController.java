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

import com.dao.IServiceRepository;
import com.entities.Service;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
	@Autowired
	IServiceRepository sr;
	
	@PostMapping("/add")
	public ResponseEntity<String> addservice(@RequestBody Service service) {
		try {
			sr.save(service);
			return ResponseEntity.status(Response.SC_CREATED).body("service ajouté");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("service non ajouté");
		}
	}
	@GetMapping("/all")
	public List<Service> getAll(){
		return (List<Service>) this.sr.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteService(@PathVariable(value = "id") int idserv) {
		try {
			Service existingservice = sr.findById(idserv).get();
			this.sr.delete(existingservice);
			return ResponseEntity.status(204).build();
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
		}

	}
}
