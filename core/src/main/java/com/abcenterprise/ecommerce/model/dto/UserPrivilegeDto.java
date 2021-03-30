package com.abcenterprise.ecommerce.model.dto;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;

@Data
public class UserPrivilegeDto implements IGenericEntity {
	
	public UserPrivilegeDto() {}
	public UserPrivilegeDto(Long id, String name) { 
		this.id = id;
		this.name = name;
	}

	private Long id;
	
	private String name;
    
}
