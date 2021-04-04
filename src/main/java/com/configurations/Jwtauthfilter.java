package com.configurations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class Jwtauthfilter extends OncePerRequestFilter {
	
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
    	System.out.println("**********************************************");
    	HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
        // Fetching the authorization header from the request.
        // This header will contain the bearer token having the jwt token.
    	System.out.println(request.getRemoteUser());
        String authenticationHeader= request.getHeader(Iconstants.HEADER);
        try {
            SecurityContext context= SecurityContextHolder.getContext();
 
            if(authenticationHeader != null && authenticationHeader.startsWith("Bearer")) {
 
                final String bearerTkn= authenticationHeader.replaceAll(Iconstants.BEARER_TOKEN, "");
                System.out.println("Following token is received from the protected url= "+ bearerTkn);
 
                try {
                    // Parsing the jwt token.
                    Jws<Claims> claims = Jwts.parser().requireIssuer(Iconstants.ISSUER).setSigningKey(Iconstants.SECRET_KEY).parseClaimsJws(bearerTkn);
 
                    int contractId = 0;
                    // Obtaining the claims from the parsed jwt token.
                    String user= (String) claims.getBody().get("usr");
                    if(claims.getBody().get("contractId") != null) {
                        contractId = (int) claims.getBody().get("contractId");
                    }
                    System.out.println(user);
                    System.out.println(contractId);
                    String roles= "";
                    
                    switch((int) claims.getBody().get("rol")){
                    case 1 : roles = "RHresponsable"; 
                    	break;
                    case 2 : roles = "Employee";
                    	break;
                    case 3 : roles = "Invite";
                    	break;
                    case 4 : roles = "Admin";
                    	break;
                    
                    }
 
                    // Creating the list of granted-authorities for the received roles.
                    List<GrantedAuthority> authority= new ArrayList<GrantedAuthority>();
                    for(String role: roles.split(","))
                        authority.add(new SimpleGrantedAuthority(role));
 
                    // Creating an authentication object using the claims.
                    Myauthtoken authenticationTkn= new Myauthtoken(user, contractId, authority);
                    // Storing the authentication object in the security context.
                    context.setAuthentication(authenticationTkn);
                } catch (SignatureException e) {
                    throw new ServletException("Invalid token.");
                }
            }
 
            filterChain.doFilter(request, response);
            context.setAuthentication(null);
        } catch(AuthenticationException ex) {
            throw new ServletException("Authentication exception.");
        }
    }

	
}