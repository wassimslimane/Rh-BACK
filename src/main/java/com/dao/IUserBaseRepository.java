package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.entities.User;
@Transactional
public interface IUserBaseRepository<T extends User> extends CrudRepository<T, Integer>  {
	
	//@Query("SELECT u FROM User u WHERE u.email = :email and u.user_type=1")
	//public User findByEmail(@Param("email") String email);
//	
//	@Query("SELECT u FROM User u WHERE u.cin = :cin")
//	public User findByCin(@Param("cin") String cin);
//	
//	@Query("SELECT u FROM User u WHERE u.firstname = :firstname")
//	public List<User> findByFirstName(@Param("firstname") String firstName);
//	
//	@Query("SELECT u FROM User u WHERE u.lastname = :lastname")
//	public List<User> findByLastName(@Param("lastname") String lastName);
}
