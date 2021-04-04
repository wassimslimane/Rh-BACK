package com.dao;

import javax.transaction.Transactional;

import com.entities.Invite;

@Transactional
public interface IInviteRepository extends IUserBaseRepository<Invite> {

}
