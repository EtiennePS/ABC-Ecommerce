package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class OptionDto extends RepresentationModel<OptionDto> implements IGenericEntity {

	private Long id;
	
	private String name;
	
	private Double price;
	
	@JsonIgnoreProperties("options")
	private ItemDto item;
}
