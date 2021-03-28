package com.abcenterprise.ecommerce.model.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.Collection;

import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

import com.abcenterprise.ecommerce.controller.impl.UserRestController;
import com.abcenterprise.ecommerce.model.dto.AddressDto;
import com.abcenterprise.ecommerce.model.dto.CartLineDto;
import com.abcenterprise.ecommerce.model.dto.CityDto;
import com.abcenterprise.ecommerce.model.dto.CountryDto;
import com.abcenterprise.ecommerce.model.dto.ItemDto;
import com.abcenterprise.ecommerce.model.dto.OptionDto;
import com.abcenterprise.ecommerce.model.dto.OrderDto;
import com.abcenterprise.ecommerce.model.dto.OrderLineDto;
import com.abcenterprise.ecommerce.model.dto.UserDto;

public abstract class HateoasMapper {
	
	// Links of entity's lists
	
	private Collection<Link> usersLinks = Arrays.asList(
		linkTo(methodOn(UserRestController.class).getAll()).withRel("users")
	);
	
	private Collection<Link> addressesLinks = Arrays.asList(
	);
	
	private Collection<Link> cartLinesLinks = Arrays.asList(
	);
	
	private Collection<Link> citiesLinks = Arrays.asList(
	);
	
	private Collection<Link> countriesLinks = Arrays.asList(
	);
	
	private Collection<Link> itemsLinks = Arrays.asList(
	);
	
	private Collection<Link> optionsLinks = Arrays.asList(
	);
	
	private Collection<Link> ordersLinks = Arrays.asList(
	);
	
	private Collection<Link> orderLinesLinks = Arrays.asList(
	);
	
	// Links of one entity
	
	@AfterMapping()
	protected UserDto addLinks(@MappingTarget UserDto u) {
		return u.add(
			linkTo(methodOn(UserRestController.class).getByUsername(u.getUsername())).withSelfRel()
		);
	}
	
	protected CollectionModel<UserDto> toUserDtoCollectionModel(Collection<UserDto> u) {
		return CollectionModel.of(u, usersLinks);
	}
	
	@AfterMapping()
	protected AddressDto addLinks(@MappingTarget AddressDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<AddressDto> toAddressDtoCollectionModel(Collection<AddressDto> u) {
		return CollectionModel.of(u, addressesLinks);
	}
	
	@AfterMapping()
	protected CartLineDto addLinks(@MappingTarget CartLineDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<CartLineDto> toCartLineDtoCollectionModel(Collection<CartLineDto> u) {
		return CollectionModel.of(u, cartLinesLinks);
	}
	
	@AfterMapping()
	protected CityDto addLinks(@MappingTarget CityDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<CityDto> toCityDtoCollectionModel(Collection<CityDto> u) {
		return CollectionModel.of(u, citiesLinks);
	}
	
	@AfterMapping()
	protected CountryDto addLinks(@MappingTarget CountryDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<CountryDto> toCountryDtoCollectionModel(Collection<CountryDto> u) {
		return CollectionModel.of(u, countriesLinks);
	}
	
	@AfterMapping()
	protected ItemDto addLinks(@MappingTarget ItemDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<ItemDto> toItemDtoCollectionModel(Collection<ItemDto> u) {
		return CollectionModel.of(u, itemsLinks);
	}
	
	@AfterMapping()
	protected OptionDto addLinks(@MappingTarget OptionDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<OptionDto> toOptionDtoCollectionModel(Collection<OptionDto> u) {
		return CollectionModel.of(u, optionsLinks);
	}
	
	@AfterMapping()
	protected OrderDto addLinks(@MappingTarget OrderDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<OrderDto> toOrderDtoCollectionModel(Collection<OrderDto> u) {
		return CollectionModel.of(u, ordersLinks);
	}
	
	@AfterMapping()
	protected OrderLineDto addLinks(@MappingTarget OrderLineDto u) {
		return u.add(
		);
	}
	
	protected CollectionModel<OrderLineDto> toOrderLineDtoCollectionModel(Collection<OrderLineDto> u) {
		return CollectionModel.of(u, orderLinesLinks);
	}
	
}