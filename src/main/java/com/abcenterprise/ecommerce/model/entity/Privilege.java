package com.abcenterprise.ecommerce.model.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity @Data
public class Privilege implements IGenericEntity, GrantedAuthority {

	public Privilege() {}
	
	public Privilege(String name) {
		this.setName(name);
	}
	
	public static final String CAN_SEE_ALL_ORDERS = "CAN_SEE_ALL_ORDERS";
	public static final String CAN_ADD_TO_CART = "CAN_ADD_TO_CART";
	public static final String CAN_VALIDATE_CART = "CAN_VALIDATE_CART";
	
	private static final long serialVersionUID = -9060355566637363342L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@JsonIgnore
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

	@Override
	public String getAuthority() {
		return this.getName();
	}
}
