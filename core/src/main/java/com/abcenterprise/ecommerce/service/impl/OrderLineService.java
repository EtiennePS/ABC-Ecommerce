package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.model.entity.OrderLine;
import com.abcenterprise.ecommerce.repository.OrderLineRepository;
import com.abcenterprise.ecommerce.service.IOrderLineService;

@Service
public class OrderLineService extends GetableService<OrderLine> implements IOrderLineService {
	
	@Autowired
	protected OrderLineRepository repository;

	@Override
	public OrderLine create(OrderLine c) {		
		return repository.save(c);
	}	
}
