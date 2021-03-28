package com.abcenterprise.ecommerce.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity @Data
public class OrderLine implements IGenericEntity {

	public OrderLine() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double amount;
	
	@Column(nullable = false)
	private Double price;
	
	@ManyToOne
	private Item item;
	
	@ManyToOne
	private Order order;
}
