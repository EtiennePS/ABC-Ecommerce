package com.abcenterprise.ecommerce.service;

import java.util.List;

import com.abcenterprise.ecommerce.model.entity.CartLine;

public interface ICartLineService extends IGetableService<CartLine>, ICreatableService<CartLine> {

	public void deleteAll(List<CartLine> cartLines);
	public List<CartLine> getAllByIds(List<Long> ids);
	public List<CartLine> getAllByConnectedUser();
	
}
