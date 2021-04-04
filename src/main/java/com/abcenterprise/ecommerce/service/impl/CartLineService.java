package com.abcenterprise.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.IllegalEntityException;
import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.model.entity.Item;
import com.abcenterprise.ecommerce.model.entity.Option;
import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.repository.CartLineRepository;
import com.abcenterprise.ecommerce.service.ICartLineService;
import com.abcenterprise.ecommerce.service.IItemService;
import com.abcenterprise.ecommerce.service.IOptionService;
import com.abcenterprise.ecommerce.service.IUserService;

@Service
public class CartLineService extends GetableService<CartLine> implements ICartLineService {
	
	@Autowired
	protected CartLineRepository repository;
	
	@Autowired
	protected IUserService userService;
	
	@Autowired
	protected IItemService itemService;
	
	@Autowired
	protected IOptionService optionService;

	@Override
	public CartLine create(CartLine c) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		c.setUser(user);
		
		if(c.getItem() == null || c.getItem().getId() == null)
			throw new IllegalEntityException("This cartLine has no item with an id");
		
		Item item = itemService.getById(c.getItem().getId());
		c.setItem(item);
		
		for(Option o : c.getSelectedOptions()) {
			if(o.getId() == null)
				throw new IllegalEntityException("All options must have an id");
			
			o = optionService.getById(o.getId());
			
			if(o.getItem().getId() != c.getItem().getId()) 
				throw new IllegalEntityException("The following option doesn't belong to the selected item : " + o);
		}
		
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
