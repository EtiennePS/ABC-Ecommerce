package com.abcenterprise.ecommerce.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.abcenterprise.ecommerce.model.dto.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api(tags="User Rest API")
public interface IUserRestController extends AuthenticationEntryPoint {
	
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get all users")
	public CollectionModel<UserDto> getAll();
	
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get a user with his id")
	public UserDto getById(@PathVariable Long id);
	
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get a user with his username")
	public UserDto getByUsername(@PathVariable String username);
	
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get a user with his username")
	public ResponseEntity<UserDto> createAuthenticationToken(@RequestBody UserDto userIn) throws Exception;
	
	@ApiOperation(authorizations = @Authorization(value = "Bearer"), value = "Get a user with his username")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) throws Exception;
	
}
