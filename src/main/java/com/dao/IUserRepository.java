package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.entities.User;

@Transactional
public interface IUserRepository extends IUserBaseRepository<User> {

	

	boolean existsByEmail(String email);

	User findByEmail(String email);

	List<User> findByIsValid(boolean isValid);
}
