package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class OptionDto extends RepresentationModel<OptionDto> implements IGenericEntity {

	private Long id;
	
	private String name;
	
	private Double price;
	
	private ItemDto item;
}
