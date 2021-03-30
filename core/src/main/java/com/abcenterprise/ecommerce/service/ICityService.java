package com.abcenterprise.ecommerce.service;

import com.abcenterprise.ecommerce.model.entity.City;

public interface ICityService extends IGetableService<City>, ICreatableService<City> {
	
	public City getByName(String name);
	
}
