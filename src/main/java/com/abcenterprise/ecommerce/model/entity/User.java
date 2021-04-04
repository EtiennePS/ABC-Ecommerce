package com.abcenterprise.ecommerce.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity @Data
public class User implements IGenericEntity, UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 25, unique = true)
	@Pattern(regexp = "^[A-Za-z0-9]{3,20}$")
	private String username;
	
	@Column(unique = true)
	@Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	private String email;
	
	@NotNull
	private String password;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column
	private String tel;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<CartLine> cartLines;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<Address> addresses;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles = new ArrayList<Role>();

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    
    private static final long serialVersionUID = 7907765523102065987L;
    
    @Override
    public Set<Privilege> getAuthorities() {
    	Set<Privilege> sp = new HashSet<Privilege>();
    	for(Role r : this.getRoles()) {
    		sp.addAll(r.getPrivileges());
    	}
        return sp;
    }
    
    public void setRoles(Collection<Role> roles) {
    	this.roles = roles;
    }
    
    public void setAllRoles(Role...roles) {
    	setRoles(Arrays.asList(roles));
    }
}
