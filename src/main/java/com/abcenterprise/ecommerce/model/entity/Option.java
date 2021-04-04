package com.abcenterprise.ecommerce.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity @Data
public class Option implements IGenericEntity {

	public Option() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private Double price;
	
	@ManyToOne
	private Item item;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<CartLine> cartLines;
}
