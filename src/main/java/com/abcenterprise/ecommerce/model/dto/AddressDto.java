package com.abcenterprise.ecommerce.model.dto;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class AddressDto extends RepresentationModel<AddressDto> implements IGenericEntity {
	
	private Long id;
	
	private String name;
	
	private String streetNum;
	
	private String street;
	
	private String line1;
	
	private String line2;
	
	private CityDto city;
	
	private UserDto user;
	
}
