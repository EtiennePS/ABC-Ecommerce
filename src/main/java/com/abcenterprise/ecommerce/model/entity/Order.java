package com.abcenterprise.ecommerce.model.entity;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity @Data
@Table(name="orders")
public class Order implements IGenericEntity {

	public Order() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private ZonedDateTime creationDate;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "order")
	private List<OrderLine> orderLines;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Address billingAddress;
	
	@ManyToOne
	private Address deliveryAddress;
}
