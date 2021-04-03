package com.abcenterprise.ecommerce.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity @Data
public class CartLine implements IGenericEntity {

	public CartLine() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double amount;
	
	@ManyToOne
	private Item item;
	
	@OneToMany
	private List<Option> selectedOptions;
	
	@ManyToOne
	private User user;
}
