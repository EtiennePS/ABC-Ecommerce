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
import com.abcenterprise.ecommerce.model.dto.CountryDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.ICountryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryRestController {
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@PreAuthorize(SecurityConfig.CAN_SEE_ALL_ORDERS)
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<CountryDto> getAll() {
		return mapper.toCountryDto(countryService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CountryDto getById(@PathVariable Long id) {
		return mapper.toDto(countryService.getById(id));
	}
	
	@PostMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public ResponseEntity<CountryDto> create(@RequestBody CountryDto countryIn) throws URISyntaxException {
		CountryDto countryOut = mapper.toDto(countryService.create(mapper.toEntity(countryIn)));
		Link self = linkTo(methodOn(CountryRestController.class).getById(countryOut.getId())).withSelfRel();
		return ResponseEntity.created(new URI(self.getHref())).body(countryOut);
	}
}
