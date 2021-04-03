package com.abcenterprise.ecommerce.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity @Data
public class Item implements IGenericEntity {

	public Item() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private Double stock;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = true)
	private Integer productionDelay;
	
	@OneToMany(mappedBy = "item")
	private List<Option> options;
	
}
