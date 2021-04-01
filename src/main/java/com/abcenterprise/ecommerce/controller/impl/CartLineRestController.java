package com.abcenterprise.ecommerce.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcenterprise.ecommerce.config.SecurityConfig;
import com.abcenterprise.ecommerce.model.dto.CartLineDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.ICartLineService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/cartLines", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartLineRestController {
	
	@Autowired
	private ICartLineService cartLineService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@PreAuthorize(SecurityConfig.CAN_SEE_ALL_ORDERS)
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<CartLineDto> getAll() {
		return mapper.toCartLineDto(cartLineService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CartLineDto getById(@PathVariable Long id) {
		return mapper.toDto(cartLineService.getById(id));
	}
	
	@GetMapping("/mines")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<CartLineDto> getAllFromUser() {
		return mapper.toCartLineDto(cartLineService.getAllByConnectedUser());
	}
}
