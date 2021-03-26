package com.abcenterprise.ecommerce.service;

import com.abcenterprise.ecommerce.model.entity.Privilege;

public interface IPrivilegeService extends IGetableService<Privilege> {
	
	public Privilege findOrCreate(String name);
	public Privilege getByName(String name);
	
}
