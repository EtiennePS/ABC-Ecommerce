package com.abcenterprise.ecommerce.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.Country;
import com.abcenterprise.ecommerce.repository.CountryRepository;
import com.abcenterprise.ecommerce.service.ICountryService;

@Service
public class CountryService extends GetableService<Country> implements ICountryService {
	
	@Autowired
	protected CountryRepository repository;

	@Override
	public Country create(Country c) {		
		return repository.save(c);
	}

	@Override
	public Country getByName(String name) {
		
		try {
			return this.repository.findByName(name).get();
		}
		catch(NoSuchElementException e) {
			throw new NoSuchEntityException("There is no country with the name " + name, e);
		}
	}
	
}
