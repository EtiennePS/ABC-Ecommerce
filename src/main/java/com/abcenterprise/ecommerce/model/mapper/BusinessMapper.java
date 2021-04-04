package com.abcenterprise.ecommerce.model.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.hateoas.CollectionModel;

import com.abcenterprise.ecommerce.model.dto.AddressDto;
import com.abcenterprise.ecommerce.model.dto.CartLineDto;
import com.abcenterprise.ecommerce.model.dto.CityDto;
import com.abcenterprise.ecommerce.model.dto.CountryDto;
import com.abcenterprise.ecommerce.model.dto.ItemDto;
import com.abcenterprise.ecommerce.model.dto.OptionDto;
import com.abcenterprise.ecommerce.model.dto.OrderDto;
import com.abcenterprise.ecommerce.model.dto.OrderLineDto;
import com.abcenterprise.ecommerce.model.entity.Address;
import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.model.entity.City;
import com.abcenterprise.ecommerce.model.entity.Country;
import com.abcenterprise.ecommerce.model.entity.Item;
import com.abcenterprise.ecommerce.model.entity.Option;
import com.abcenterprise.ecommerce.model.entity.Order;
import com.abcenterprise.ecommerce.model.entity.OrderLine;

@Mapper(componentModel = "spring")
public abstract class BusinessMapper extends HateoasMapper {
	
	public abstract Address toEntity(AddressDto dto);
	public abstract Collection<Address> toAddressEntity(Collection<AddressDto> dto);
	
	public abstract AddressDto toDto(Address e);
	protected abstract Collection<AddressDto> toAddressDtoNoLinks(Collection<Address> e);
	public CollectionModel<AddressDto> toAddressDto(Collection<Address> e) {
		return toAddressDtoCollectionModel(toAddressDtoNoLinks(e));
	}
	
	public abstract CartLine toEntity(CartLineDto dto);
	public abstract Collection<CartLine> toCartLineEntity(Collection<CartLineDto> dto);
	
	public abstract CartLineDto toDto(CartLine e);
	protected abstract Collection<CartLineDto> toCartLineDtoNoLinks(Collection<CartLine> e);
	public CollectionModel<CartLineDto> toCartLineDto(Collection<CartLine> e) {
		return toCartLineDtoCollectionModel(toCartLineDtoNoLinks(e));
	}
	
	public abstract City toEntity(CityDto dto);
	public abstract Collection<City> toCityEntity(Collection<CityDto> dto);
	
	public abstract CityDto toDto(City e);
	protected abstract Collection<CityDto> toCityDtoNoLinks(Collection<City> e);
	public CollectionModel<CityDto> toCityDto(Collection<City> e) {
		return toCityDtoCollectionModel(toCityDtoNoLinks(e));
	}
	
	public abstract Country toEntity(CountryDto dto);
	public abstract Collection<Country> toCountryEntity(Collection<CountryDto> dto);
	
	public abstract CountryDto toDto(Country e);
	protected abstract Collection<CountryDto> toCountryDtoNoLinks(Collection<Country> e);
	public CollectionModel<CountryDto> toCountryDto(Collection<Country> e) {
		return toCountryDtoCollectionModel(toCountryDtoNoLinks(e));
	}
	
	public abstract Item toEntity(ItemDto dto);
	public abstract Collection<Item> toItemEntity(Collection<ItemDto> dto);
	
	@Mapping( target = "options", qualifiedByName = "noItem")
	public abstract ItemDto toDto(Item e);
	protected abstract Collection<ItemDto> toItemDtoNoLinks(Collection<Item> e);
	public CollectionModel<ItemDto> toItemDto(Collection<Item> e) {
		return toItemDtoCollectionModel(toItemDtoNoLinks(e));
	}
	
	public abstract Option toEntity(OptionDto dto);
	public abstract Collection<Option> toOptionEntity(Collection<OptionDto> dto);
	
	@Mapping(target = "item.options", ignore = true) // To prevent infinite mapping loop
	public abstract OptionDto toDto(Option e);
	@Mapping(target = "item", ignore = true)
	@Named("noItem")
	public abstract OptionDto toDtoNoItem(Option e);
	protected abstract Collection<OptionDto> toOptionDtoNoLinks(Collection<Option> e);
	public CollectionModel<OptionDto> toOptionDto(Collection<Option> e) {
		return toOptionDtoCollectionModel(toOptionDtoNoLinks(e));
	}
	
	public abstract Order toEntity(OrderDto dto);
	public abstract Collection<Order> toOrderEntity(Collection<OrderDto> dto);
	
	@Mapping( target = "orderLines", qualifiedByName = "noOrder")
	public abstract OrderDto toDto(Order e);
	protected abstract Collection<OrderDto> toOrderDtoNoLinks(Collection<Order> e);
	public CollectionModel<OrderDto> toOrderDto(Collection<Order> e) {
		return toOrderDtoCollectionModel(toOrderDtoNoLinks(e));
	}
	
	public abstract OrderLine toEntity(OrderLineDto dto);
	public abstract Collection<OrderLine> toOrderLineEntity(Collection<OrderLineDto> dto);
	public abstract OrderLineDto toDto(OrderLine e);
	@Mapping(target = "order", ignore = true)
	@Named("noOrder")
	public abstract OrderLineDto toDtoNoOrder(OrderLine e);
	protected abstract Collection<OrderLineDto> toOrderLineDtoNoLinks(Collection<OrderLine> e);
	public CollectionModel<OrderLineDto> toOrderLineDto(Collection<OrderLine> e) {
		return toOrderLineDtoCollectionModel(toOrderLineDtoNoLinks(e));
	}
}
