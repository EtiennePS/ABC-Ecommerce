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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<CartLineDto> getAll() {
		return mapper.toCartLineDto(cartLineService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CartLineDto getById(@PathVariable Long id) {
		return mapper.toDto(cartLineService.getById(id));
	}
	
	@PostMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public ResponseEntity<CartLineDto> create(@RequestBody CartLineDto cartLineIn) throws URISyntaxException {
		CartLineDto cartLineOut = mapper.toDto(cartLineService.create(mapper.toEntity(cartLineIn)));
		Link self = linkTo(methodOn(CartLineRestController.class).getById(cartLineOut.getId())).withSelfRel();
		return ResponseEntity.created(new URI(self.getHref())).body(cartLineOut);
	}
}
