package com.abcenterprise.ecommerce.service;

import java.util.Collection;

import com.abcenterprise.ecommerce.model.entity.Privilege;
import com.abcenterprise.ecommerce.model.entity.Role;

public interface IRoleService extends IGetableService<Role> {
	
	public Role findOrCreate(String name, Collection<Privilege> privileges);
	public Role findOrCreate(String name, Privilege... privileges);
	public Role getByName(String name);
	
}
