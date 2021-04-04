package com.controller;


import java.util.List;
import java.util.Properties;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.dao.IRHresponsableRepository;
import com.dao.IEmployeeRepositoy;
import com.dao.IInviteRepository;
import com.dao.IUserRepository;
import com.entities.RHresponsable;
import com.entities.Employee;
import com.entities.Invite;
import com.entities.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	IUserRepository ur;

	@Autowired
	IRHresponsableRepository cr;

	@Autowired
	IInviteRepository ipr;

	@Autowired
	IEmployeeRepositoy er;

	@PostMapping("/adduser")
	public ResponseEntity<String> adduser(@RequestBody User user) {
		try {
			ur.save(user);
			return ResponseEntity.status(Response.SC_CREATED).body("Utilisateur ajouté");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Utilisateur n'est pas ajouté");
		}
	}

	@PostMapping("/addRHresponsable")
	public ResponseEntity<String> addRHresponsable(@RequestBody RHresponsable rhresponsable) {
		try {
			cr.save(rhresponsable);
			return ResponseEntity.status(Response.SC_CREATED).body("RHresponsable ajouté");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("RHresponsable n'est pas ajouté");
		}
	}
	@PutMapping("/updateRH")
	public ResponseEntity<String> updateRH(@RequestBody RHresponsable rhresponsable) {
		try {
			cr.save(rhresponsable);
			return ResponseEntity.status(Response.SC_CREATED).body("RHresponsable modifié");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("RHresponsable n'est pas modifié");
		}
	}
	
	@PutMapping("/updatemp")
	public ResponseEntity<String> updatemp(@RequestBody Employee employee) {
		try {
			er.save(employee);
			return ResponseEntity.status(Response.SC_CREATED).body("employee modifié");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("employee n'est pas modifié");
		}
	}

	@PostMapping("/addEmployes")
	public ResponseEntity<String> addEmployes(@RequestBody Employee employee) {
		try {
			er.save(employee);
			return ResponseEntity.status(Response.SC_CREATED).body("Employée ajouté");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Employée n'est pas ajouté");
		}
	}

	@PostMapping("/addInvite")
	public ResponseEntity<String> addInvite(@RequestBody Invite invite) {
		try {
			ipr.save(invite);
			return ResponseEntity.status(Response.SC_CREATED).body("Invité ajouté");
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body("Invité n'est pas ajouté");
		}
	}

	@DeleteMapping("/delete/{idUser}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "idUser") int idUser) {
		try {
			User existingUser = ur.findById(idUser).get();
			this.ur.delete(existingUser);
			return ResponseEntity.status(204).build();
		} catch (Exception e) {
			return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
		}

	}
	
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("wassimslimane@gmail.com");
	    mailSender.setPassword("123456789");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	@PutMapping("/approve/{idUser}")
	public ResponseEntity<String> approveUser(@PathVariable(value = "idUser") int idUser){
		User user_to_approve = ur.findById(idUser).get();
		user_to_approve.setValid(true);
		ur.save(user_to_approve);
		try {
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("noreply@pi-esprit.com");
	        message.setTo(user_to_approve.getEmail()); 
	        message.setSubject("Account Approved!"); 
	        message.setText("Congratulations! Your account has been approved by an admin!");
	        this.getJavaMailSender().send(message);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ResponseEntity.status(200).body("Approved!");
	}
	
	@PutMapping("/update/{idUser}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "idUser") int idUser, @RequestBody User user) {

		User user_to_update = ur.findById(idUser).get();

		// The user fetched is a RHresponsable
		if (user_to_update instanceof RHresponsable) {
			RHresponsable rhresponsable = (RHresponsable) user_to_update;
			RHresponsable temp_user = (RHresponsable) user;
			if (temp_user.getFirstName() != null && temp_user.getFirstName() != "") {
				rhresponsable.setFirstName(temp_user.getFirstName());
			}
			if (temp_user.getLastName() != null && temp_user.getLastName() != "") {
				rhresponsable.setLastName(temp_user.getLastName());
			}
			if (temp_user.getCin() != null && temp_user.getCin() != "") {
				rhresponsable.setCin(temp_user.getCin());
			}
			if (temp_user.getEmail() != null && temp_user.getEmail() != "") {
				rhresponsable.setEmail(temp_user.getEmail());
			}
			if (temp_user.getPwd() != null && temp_user.getPwd() != "") {
				rhresponsable.setPwd(temp_user.getPwd());
			}
			if (temp_user.getImg() != null && temp_user.getImg() != "") {
				rhresponsable.setImg(temp_user.getImg());
			}
			if (temp_user.getAdresse() != null && temp_user.getAdresse() != "") {
				rhresponsable.setAdresse(temp_user.getAdresse());
			}

			ur.save(rhresponsable);
			return ResponseEntity.status(204).build();
		}

		// The user fetched is an Employee
		if (user_to_update instanceof Employee) {
			Employee employee = (Employee) user_to_update;
			Employee temp_user = (Employee) user;

			if (temp_user.getFirstName() != null && temp_user.getFirstName() != "") {
				employee.setFirstName(temp_user.getFirstName());
			}
			if (temp_user.getLastName() != null && temp_user.getLastName() != "") {
				employee.setLastName(temp_user.getLastName());
			}
			if (temp_user.getCin() != null && temp_user.getCin() != "") {
				employee.setCin(temp_user.getCin());
			}
			if (temp_user.getEmail() != null && temp_user.getEmail() != "") {
				employee.setEmail(temp_user.getEmail());
			}
			if (temp_user.getPwd() != null && temp_user.getPwd() != "") {
				employee.setPwd(temp_user.getPwd());
			}
			if (temp_user.getDateDembauche() != null) {
				employee.setDateDembauche(temp_user.getDateDembauche());
			}
			if (temp_user.getDateNaissance() != null) {
				employee.setDateNaissance(temp_user.getDateNaissance());
			}
			if (temp_user.getDateSortie() != null) {
				employee.setDateSortie(temp_user.getDateSortie());
			}
			if (temp_user.getPoste() != null) {
				employee.setPoste(temp_user.getPoste());
			}
			ur.save(employee);
			return ResponseEntity.status(204).build();
		}

		// The fetched user is an Insurance_Partner
		if (user_to_update instanceof Invite) {
			Invite invite = (Invite) user_to_update;
			Invite temp_user = (Invite) user;

			if (temp_user.getFirstName() != null && temp_user.getFirstName() != "") {
				invite.setFirstName(temp_user.getFirstName());
			}
			if (temp_user.getLastName() != null && temp_user.getLastName() != "") {
				invite.setLastName(temp_user.getLastName());
			}
			if (temp_user.getCin() != null && temp_user.getCin() != "") {
				invite.setCin(temp_user.getCin());
			}
			if (temp_user.getEmail() != null && temp_user.getEmail() != "") {
				invite.setEmail(temp_user.getEmail());
			}
			if (temp_user.getPwd() != null && temp_user.getPwd() != "") {
				invite.setPwd(temp_user.getPwd());
			}

			ur.save(invite);
			return ResponseEntity.status(204).build();
		}

		// Not a valid User type
		return ResponseEntity.status(Response.SC_BAD_REQUEST).build();
	}

	
	@GetMapping("/all")
	public List<User> getAll(){
		return (List<User>) this.ur.findAll();
	}
	@GetMapping("/allemp")
	public List<Employee> getAllemp(){
		return (List<Employee>) this.er.findAll();
	}
	@GetMapping("/not-approved")
	public List<User> getAllNotApproved(){
		return (List<User>) this.ur.findByIsValid(false);
	}
	
}
