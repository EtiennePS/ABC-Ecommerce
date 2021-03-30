package com.abcenterprise.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.repository.CartLineRepository;
import com.abcenterprise.ecommerce.service.ICartLineService;
import com.abcenterprise.ecommerce.service.IUserService;

@Service
public class CartLineService extends GetableService<CartLine> implements ICartLineService {
	
	@Autowired
	protected CartLineRepository repository;
	
	@Autowired
	protected IUserService userService;

	@Override
	public CartLine create(CartLine c) {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		User user = userService.getByUsername(username);
		c.setUser(user);
		
		return repository.save(c);
	}

	@Override
	public void deleteAll(List<CartLine> cartLines) {
		repository.deleteAll(cartLines);
	}

	@Override
	public void getAllByIds(List<Long> ids) {
		repository.findAllById(ids);
	}	
}
