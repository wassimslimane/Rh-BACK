package com.dao;

import javax.transaction.Transactional;

import com.entities.Employee;

@Transactional
public interface IEmployeeRepositoy extends IUserBaseRepository<Employee> {

}
