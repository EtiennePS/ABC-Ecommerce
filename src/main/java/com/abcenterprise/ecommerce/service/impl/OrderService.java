package com.abcenterprise.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.exception.IllegalEntityException;
import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.model.entity.Order;
import com.abcenterprise.ecommerce.model.entity.OrderLine;
import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.repository.OrderRepository;
import com.abcenterprise.ecommerce.service.ICartLineService;
import com.abcenterprise.ecommerce.service.IOrderLineService;
import com.abcenterprise.ecommerce.service.IOrderService;
import com.abcenterprise.ecommerce.service.IUserService;

@Service
public class OrderService extends GetableService<Order> implements IOrderService {
	
	@Autowired
	protected OrderRepository repository;
	
	@Autowired
	protected IUserService userService;
	
	@Autowired
	protected ICartLineService cartLineService;
	
	@Autowired
	protected IOrderLineService orderLineService;

	@Override
	public Order create(Order o, List<Long> cartLineIds) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getCartLines().isEmpty())
			throw new IllegalEntityException("There is nothing in the cart of " + user.getUsername() + "!");
		
		o.setUser(user);
		
		List<CartLine> cartLines = user.getCartLines().stream().filter(c -> cartLineIds.contains(c.getId())).toList();
		
		if(cartLineIds.size() != cartLines.size())
			throw new IllegalEntityException("One of the cartLineId doesn't exist or doesn't belong to the user or is duplicate");
		
		o.setOrderLines(new ArrayList<OrderLine>());
		
		for(CartLine cl : cartLines) {
			o.getOrderLines().add(new OrderLine(cl, o)); //transform cartLine into OrderLine
		}
		
		o.setCreationDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")));
		o = repository.save(o);
		
		for(OrderLine ol : o.getOrderLines()) {
			this.orderLineService.create(ol);
		}
		
		cartLineService.deleteAll(cartLineService.getAllByIds(cartLineIds)); //remove the lines from the cart
		
		return repository.getOne(o.getId());
	}
	
	@Override
	public List<Order> getAllByConnectedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return repository.findAllByUser(user);
	}	
}
