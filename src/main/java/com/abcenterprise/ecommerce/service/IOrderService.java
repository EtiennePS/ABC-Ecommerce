package com.abcenterprise.ecommerce.service;

import java.util.List;

import com.abcenterprise.ecommerce.model.entity.Order;

public interface IOrderService extends IGetableService<Order> {
	public Order create(Order o, List<Long> cartLineIds);
}
