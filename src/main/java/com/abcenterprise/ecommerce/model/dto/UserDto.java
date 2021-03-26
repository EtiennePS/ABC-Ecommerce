package com.abcenterprise.ecommerce.model.dto;

import java.util.Collection;

import javax.validation.constraints.Pattern;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class UserDto extends RepresentationModel<UserDto> implements IGenericEntity {

	private Long id;
	
	@Pattern(regexp = "^[A-Za-z0-9]{3,20}$")
	private String username;
	
	@Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonInclude(value=Include.NON_EMPTY)
	@JsonProperty(access = Access.READ_ONLY)
	private String token;
    
    private Collection<UserPrivilegeDto> privileges;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
