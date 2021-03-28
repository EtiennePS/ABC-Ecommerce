package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.model.entity.Order;
import com.abcenterprise.ecommerce.repository.OrderRepository;
import com.abcenterprise.ecommerce.service.IOrderService;

@Service
public class OrderService extends GetableService<Order> implements IOrderService {
	
	@Autowired
	protected OrderRepository repository;

	@Override
	public Order create(Order c) {		
		return repository.save(c);
	}	
}
