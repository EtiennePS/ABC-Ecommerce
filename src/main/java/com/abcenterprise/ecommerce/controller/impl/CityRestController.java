package com.abcenterprise.ecommerce.controller.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcenterprise.ecommerce.config.SecurityConfig;
import com.abcenterprise.ecommerce.model.dto.CityDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.ICityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityRestController {
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@PreAuthorize(SecurityConfig.CAN_SEE_ALL_ORDERS)
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<CityDto> getAll() {
		return mapper.toCityDto(cityService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CityDto getById(@PathVariable Long id) {
		return mapper.toDto(cityService.getById(id));
	}
	
	@PostMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public ResponseEntity<CityDto> create(@RequestBody CityDto cityIn) throws URISyntaxException {
		CityDto cityOut = mapper.toDto(cityService.create(mapper.toEntity(cityIn)));
		Link self = linkTo(methodOn(CityRestController.class).getById(cityOut.getId())).withSelfRel();
		return ResponseEntity.created(new URI(self.getHref())).body(cityOut);
	}
}
