package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.repository.CartLineRepository;
import com.abcenterprise.ecommerce.service.ICartLineService;

@Service
public class CartLineService extends GetableService<CartLine> implements ICartLineService {
	
	@Autowired
	protected CartLineRepository repository;

	@Override
	public CartLine create(CartLine c) {		
		return repository.save(c);
	}	
}
