package com.abcenterprise.ecommerce.model.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity @Data
public class Role implements IGenericEntity {
	
	public Role() {}
	
	public Role(String name) {
		this.setName(name);
	}
	
	public Role(String name, Collection<Privilege> cp) {
		this(name);
		this.setPrivileges(cp);
	}
	
	public static final String ADMINISTRATOR = "ADMINISTRATOR";
	public static final String CUSTOMER = "CUSTOMER";
	public static final String COMMERCIAL = "COMMERCIAL";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@JsonIgnore
    @ManyToMany(mappedBy = "roles")	
    private Collection<User> users;
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;  
}
