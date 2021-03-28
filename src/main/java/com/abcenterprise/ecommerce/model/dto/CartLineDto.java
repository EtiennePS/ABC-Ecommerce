package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class CartLineDto extends RepresentationModel<CartLineDto> implements IGenericEntity {
	
	private Long id;
	
	private Double amount;
	
	private ItemDto itemDto;
	
	private UserDto user;
}
