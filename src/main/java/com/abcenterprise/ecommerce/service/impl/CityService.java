package com.abcenterprise.ecommerce.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.City;
import com.abcenterprise.ecommerce.repository.CityRepository;
import com.abcenterprise.ecommerce.service.ICityService;

@Service
public class CityService extends GetableService<City> implements ICityService {
	
	@Autowired
	protected CityRepository repository;

	@Override
	public City create(City c) {		
		return repository.save(c);
	}

	@Override
	public City getByName(String name) {
		
		try {
			return this.repository.findByName(name).get();
		}
		catch(NoSuchElementException e) {
			throw new NoSuchEntityException("There is no city with the name " + name, e);
		}
	}
	
}
