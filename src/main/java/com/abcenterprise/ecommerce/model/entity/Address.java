package com.abcenterprise.ecommerce.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity @Data
public class Address implements IGenericEntity {

	public Address() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true)
	private String streetNum;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = true)
	private String line1;
	
	@Column(nullable = true)
	private String line2;
	
	@ManyToOne
	private City city;
	
	@ManyToOne
	private User user;
	
}
