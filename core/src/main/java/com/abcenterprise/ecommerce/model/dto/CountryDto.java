package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class CountryDto extends RepresentationModel<CountryDto> implements IGenericEntity {

	private Long id;
	
	private String name;
	
	private Boolean isDeliverable;
}
