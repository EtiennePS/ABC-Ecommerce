package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class CityDto extends RepresentationModel<CityDto> implements IGenericEntity {

	private Long id;
	
	private String name;
	
	private String postalCode;
	
	private CountryDto country;
}
