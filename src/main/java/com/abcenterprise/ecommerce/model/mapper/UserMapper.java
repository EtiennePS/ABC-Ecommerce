package com.abcenterprise.ecommerce.model.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.hateoas.CollectionModel;

import com.abcenterprise.ecommerce.model.dto.UserDto;
import com.abcenterprise.ecommerce.model.dto.UserPrivilegeDto;
import com.abcenterprise.ecommerce.model.entity.Privilege;
import com.abcenterprise.ecommerce.model.entity.User;

@Mapper(componentModel="spring")
public abstract class UserMapper extends HateoasMapper {
	
	public abstract User toEntity(UserDto dto);
	
	public abstract Collection<User> toEntity(Collection<UserDto> dto);
	
	@Mapping(source="authorities", target="privileges")
	public abstract UserDto toDto(User u);
	
	protected abstract Collection<UserDto> toUserDtoNoLinks(Collection<User> u);
	
	public CollectionModel<UserDto> toUserDto(Collection<User> u) {
		return this.toUserDtoCollectionModel(toUserDtoNoLinks(u));
	}
	
	public abstract Collection<UserPrivilegeDto> toPrivilegeDto(Collection<Privilege> u);
	
	public UserPrivilegeDto map(Privilege p) {
		return new UserPrivilegeDto(p.getId(), p.getName());
	}
	
}
