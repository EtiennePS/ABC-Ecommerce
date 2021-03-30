package com.abcenterprise.ecommerce.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.Item;
import com.abcenterprise.ecommerce.repository.ItemRepository;
import com.abcenterprise.ecommerce.service.IItemService;

@Service
public class ItemService extends GetableService<Item> implements IItemService {
	
	@Autowired
	protected ItemRepository repository;

	@Override
	public Item create(Item i) {		
		return repository.save(i);
	}

	@Override
	public Item getByName(String name) {
		
		try {
			return this.repository.findByName(name).get();
		}
		catch(NoSuchElementException e) {
			throw new NoSuchEntityException("There is no item with the name " + name, e);
		}
	}
	
}
