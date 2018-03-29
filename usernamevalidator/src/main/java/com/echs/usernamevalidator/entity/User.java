package com.echs.usernamevalidator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(
	    name="User.findByName",
	    query="SELECT u FROM User u WHERE u.name = :name"
)
public class User {
	
	@Id
	@SequenceGenerator(name="genUser", sequenceName = "seqUser", initialValue=1)
	@GeneratedValue(generator="genUser")
	private long id;
	
	private String name;
	
	protected User()
	{
		
	}
	
	public User(String name) {
		super();
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	

}
