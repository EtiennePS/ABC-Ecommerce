package com.abcenterprise.ecommerce.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity @Data
public class OrderLine implements IGenericEntity {

	public OrderLine() {}
	
	public OrderLine(CartLine cl, Order order) {
		this.amount = cl.getAmount();
		this.price = cl.getPrice();
		this.item = cl.getItem();
		this.selectedOptions = cl.getSelectedOptions();
		this.order = order;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double amount;
	
	@Column(nullable = false)
	private Double price;
	
	@ManyToOne
	private Item item;
	
	@ManyToMany
	private List<Option> selectedOptions;
	
	@ManyToOne
	private Order order;
}
