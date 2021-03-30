package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class OrderLineDto extends RepresentationModel<OrderLineDto> implements IGenericEntity {

	private Long id;
	
	private Double amount;
	
	private Double price;
	
	private ItemDto itemDto;
	
	private OrderDto orderDto;
}
