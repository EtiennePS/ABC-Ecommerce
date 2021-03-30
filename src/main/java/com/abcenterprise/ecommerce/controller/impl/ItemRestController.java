package com.abcenterprise.ecommerce.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcenterprise.ecommerce.model.dto.ItemDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.IItemService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemRestController {
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<ItemDto> getAll() {
		return mapper.toItemDto(itemService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public ItemDto getById(@PathVariable Long id) {
		return mapper.toDto(itemService.getById(id));
	}
}
