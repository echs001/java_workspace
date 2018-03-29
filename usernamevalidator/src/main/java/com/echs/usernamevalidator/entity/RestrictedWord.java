package com.echs.usernamevalidator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="seqRW", initialValue=1)
@Table(name= "RestrictedWord")
@NamedQuery(
	    name="RestrictedWord.findByName",
	    query="SELECT rw FROM RestrictedWord rw WHERE :name like CONCAT('%', rw.name, '%')"
)
public class RestrictedWord {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqRW")
	private Long id;
	
	private String name;

	public RestrictedWord()
	{		
	}
	
	public RestrictedWord(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
