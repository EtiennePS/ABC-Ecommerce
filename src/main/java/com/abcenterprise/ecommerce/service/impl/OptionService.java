package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.IllegalEntityException;
import com.abcenterprise.ecommerce.model.entity.Item;
import com.abcenterprise.ecommerce.model.entity.Option;
import com.abcenterprise.ecommerce.repository.OptionRepository;
import com.abcenterprise.ecommerce.service.IItemService;
import com.abcenterprise.ecommerce.service.IOptionService;

@Service
public class OptionService extends GetableService<Option> implements IOptionService {
	
	@Autowired
	protected OptionRepository repository;
	
	@Autowired
	protected IItemService itemService;

	@Override
	public Option create(Option o) {
		if(o.getItem() == null || o.getItem().getId() == null)
			throw new IllegalEntityException("This option has no item with an id");
		
		Item item = itemService.getById(o.getItem().getId());
		o.setItem(item);
		
		return repository.save(o);
	}	
}
