package com.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Request.Login;
import com.Request.TokenResponse;
import com.configurations.Iconstants;
import com.dao.IUserRepository;

import com.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthenticationController {
	

	
	@Autowired
	private IUserRepository ur;

	  @PostMapping("/login")
	    public ResponseEntity<TokenResponse> getToken(@RequestBody Login login) throws ServletException {
	 
	        String jwttoken = "";
	        System.out.println(login.getEmail());
	 
	        // If the username and password fields are empty -> Throw an exception!
	        if(login.getEmail().isEmpty() || login.getPassword().isEmpty())
	            return new ResponseEntity<TokenResponse>(new TokenResponse(), HttpStatus.BAD_REQUEST);
	 
	        String name = login.getEmail(), 
	                password = login.getPassword();
	        
	        User user = ur.findByEmail(name);
	        
	        // If the username and password are not valid -> Thrown an invalid credentials exception!
	        if(!(name.equalsIgnoreCase(user.getEmail()) && password.equalsIgnoreCase(user.getPwd())))
	            return new ResponseEntity<TokenResponse>(new TokenResponse(), HttpStatus.UNAUTHORIZED);
	        else {
	            // Creating JWT using the user credentials.
	            Map<String, Object> claims = new HashMap<String, Object>();
	            claims.put("usr", login.getEmail());
	            claims.put("sub", "Authentication token");
	            claims.put("iss", Iconstants.ISSUER);
				claims.put("rol", user.getUser_type());
				
	            claims.put("iat", LocalDateTime.now().getSecond());
	            // .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
	            jwttoken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY).compact();
	            System.out.println("Returning the following token to the user= "+ jwttoken);
	        }
	        TokenResponse T = new TokenResponse();
	        T.setAccessToken(jwttoken);
	        return new ResponseEntity<TokenResponse>(T, HttpStatus.OK);
	    }
}
