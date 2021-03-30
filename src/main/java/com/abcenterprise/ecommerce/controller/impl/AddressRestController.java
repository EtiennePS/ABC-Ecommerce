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
import com.abcenterprise.ecommerce.model.dto.AddressDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.IAddressService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressRestController {
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@PreAuthorize(SecurityConfig.CAN_SEE_ALL_ORDERS)
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<AddressDto> getAll() {
		return mapper.toAddressDto(addressService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public AddressDto getById(@PathVariable Long id) {
		return mapper.toDto(addressService.getById(id));
	}
	
	@PostMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressIn) throws URISyntaxException {
		AddressDto addressOut = mapper.toDto(addressService.create(mapper.toEntity(addressIn)));
		Link self = linkTo(methodOn(AddressRestController.class).getById(addressOut.getId())).withSelfRel();
		return ResponseEntity.created(new URI(self.getHref())).body(addressOut);
	}
}
