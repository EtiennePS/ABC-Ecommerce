package com.abcenterprise.ecommerce.model.dto;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class OrderDto extends RepresentationModel<OrderDto> implements IGenericEntity {

	private Long id;
	
	private ZonedDateTime creationDate;
	
	private List<OrderLineDto> orderLines;
	
	private UserDto user;
	
	private AddressDto billingAddress;
	
	private AddressDto deliveryAddress;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Long> cartLinesIds;
}
