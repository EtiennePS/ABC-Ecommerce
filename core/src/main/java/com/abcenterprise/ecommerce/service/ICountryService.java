package com.abcenterprise.ecommerce.service;

import com.abcenterprise.ecommerce.model.entity.Country;

public interface ICountryService extends IGetableService<Country>, ICreatableService<Country> {
	
	public Country getByName(String name);
	
}
