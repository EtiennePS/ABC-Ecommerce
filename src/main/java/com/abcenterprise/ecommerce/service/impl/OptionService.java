package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.model.entity.Option;
import com.abcenterprise.ecommerce.repository.OptionRepository;
import com.abcenterprise.ecommerce.service.IOptionService;

@Service
public class OptionService extends GetableService<Option> implements IOptionService {
	
	@Autowired
	protected OptionRepository repository;

	@Override
	public Option create(Option c) {		
		return repository.save(c);
	}	
}
