package com.abcenterprise.ecommerce.service;

import com.abcenterprise.ecommerce.model.entity.Item;

public interface IItemService extends IGetableService<Item>, ICreatableService<Item> {
	
	public Item getByName(String name);
	
}
