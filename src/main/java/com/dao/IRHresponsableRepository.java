package com.dao;

import javax.transaction.Transactional;

import com.entities.RHresponsable;

@Transactional
public interface IRHresponsableRepository extends IUserBaseRepository<RHresponsable> {

}
