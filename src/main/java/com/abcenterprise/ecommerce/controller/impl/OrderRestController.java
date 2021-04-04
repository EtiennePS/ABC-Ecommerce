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
import com.abcenterprise.ecommerce.model.dto.CartLineDto;
import com.abcenterprise.ecommerce.model.dto.OrderDto;
import com.abcenterprise.ecommerce.model.mapper.BusinessMapper;
import com.abcenterprise.ecommerce.service.IOrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private BusinessMapper mapper;
	
	@GetMapping("/")
	@PreAuthorize(SecurityConfig.CAN_SEE_ALL_ORDERS)
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<OrderDto> getAll() {
		return mapper.toOrderDto(orderService.getAll());
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public OrderDto getById(@PathVariable Long id) {
		return mapper.toDto(orderService.getById(id));
	}
	
	@GetMapping("/mines")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public CollectionModel<OrderDto> getAllFromUser() {
		return mapper.toOrderDto(orderService.getAllByConnectedUser());
	}
	
	@PostMapping("/")
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "")
	public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderIn) throws URISyntaxException {
		OrderDto orderOut = mapper.toDto(orderService.create(mapper.toEntity(orderIn), orderIn.getCartLinesIds()));
		Link self = linkTo(methodOn(OrderRestController.class).getById(orderOut.getId())).withSelfRel();
		return ResponseEntity.created(new URI(self.getHref())).body(orderOut);
	}
}
