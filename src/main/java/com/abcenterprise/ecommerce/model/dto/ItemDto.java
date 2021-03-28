package com.abcenterprise.ecommerce.model.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = false)
public class ItemDto extends RepresentationModel<ItemDto> implements IGenericEntity {

	public ItemDto() {}
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double stock;
	
	private Double price;
	
	private Integer productionDelay;
	
	private List<OptionDto> optionDtos;
}
