package com.abcenterprise.ecommerce.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcenterprise.ecommerce.model.dto.OptionDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.IOptionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/options", produces = MediaType.APPLICATION_JSON_VALUE)
public class OptionRestController {
	
	@Autowired
	private IOptionService optionService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<OptionDto> getAll() {
		return mapper.toOptionDto(optionService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public OptionDto getById(@PathVariable Long id) {
		return mapper.toDto(optionService.getById(id));
	}
}
