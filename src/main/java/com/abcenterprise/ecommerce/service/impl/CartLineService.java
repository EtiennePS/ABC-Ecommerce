package com.abcenterprise.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.IllegalEntityException;
import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.model.entity.Item;
import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.repository.CartLineRepository;
import com.abcenterprise.ecommerce.service.ICartLineService;
import com.abcenterprise.ecommerce.service.IItemService;
import com.abcenterprise.ecommerce.service.IUserService;

@Service
public class CartLineService extends GetableService<CartLine> implements ICartLineService {
	
	@Autowired
	protected CartLineRepository repository;
	
	@Autowired
	protected IUserService userService;
	
	@Autowired
	protected IItemService itemService;

	@Override
	public CartLine create(CartLine c) {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		User user = userService.getByUsername(username);
		c.setUser(user);
		
		if(c.getItem() == null || c.getItem().getId() == null)
			throw new IllegalEntityException("This cartLine has no item with an id");
		
		Item item = itemService.getById(c.getItem().getId());
		c.setItem(item);
		
		return repository.save(c);
	}

	@Override
	public void deleteAll(List<CartLine> cartLines) {
		repository.deleteAll(cartLines);
	}

	@Override
	public List<CartLine> getAllByIds(List<Long> ids) {
		return repository.findAllById(ids);
	}
	
	@Override
	public List<CartLine> getAllByConnectedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return repository.findAllByUser(user);
	}	
}
