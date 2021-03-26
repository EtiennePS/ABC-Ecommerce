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
import com.abcenterprise.ecommerce.model.dto.UserDto;

public abstract class HateoasMapper {
	
	// Links of lists
	
	private Collection<Link> usersLinks = Arrays.asList(
		linkTo(methodOn(UserRestController.class).getAll()).withRel("users")
	);
	
	@AfterMapping()
	protected UserDto addLinks(@MappingTarget UserDto u) {
		return u.add(
			linkTo(methodOn(UserRestController.class).getByUsername(u.getUsername())).withSelfRel()
		);
	}
	
	protected CollectionModel<UserDto> toUserDtoCollectionModel(Collection<UserDto> u) {
		return CollectionModel.of(u, usersLinks);
	}
	
}