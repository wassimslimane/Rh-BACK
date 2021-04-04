package com.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="Invite")
@DiscriminatorValue("3")
public class Invite extends User {

}
