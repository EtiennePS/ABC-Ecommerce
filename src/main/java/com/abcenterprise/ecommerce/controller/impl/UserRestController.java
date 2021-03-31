package com.abcenterprise.ecommerce.controller.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcenterprise.ecommerce.advice.AbcExceptionHandler;
import com.abcenterprise.ecommerce.config.JwtTokenUtil;
import com.abcenterprise.ecommerce.config.SecurityConfig;
import com.abcenterprise.ecommerce.controller.IUserRestController;
import com.abcenterprise.ecommerce.model.dto.ErrorDto;
import com.abcenterprise.ecommerce.model.dto.UserDto;
import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.model.mapper.UserMapper;
import com.abcenterprise.ecommerce.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController implements AuthenticationEntryPoint, IUserRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/")
	@PreAuthorize(SecurityConfig.CAN_SEE_ALL_ORDERS)
	public CollectionModel<UserDto> getAll() {
		return userMapper.toUserDto(userService.getAll());
	}
	
	@GetMapping("/id/{id}")
	public UserDto getById(@PathVariable Long id) {
		return userMapper.toDto(userService.getById(id));
	}
	
	@GetMapping("/name/{username}")
	public UserDto getByUsername(@PathVariable String username) {
		return userMapper.toDto(userService.getByUsername(username));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<UserDto> createAuthenticationToken(@RequestBody UserDto userIn) throws Exception {
		String login = !StringUtils.hasLength(userIn.getEmail()) ? userIn.getUsername() : userIn.getEmail();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, userIn.getPassword()));
		User user = userService.getByUsernameOrEmail(login);
		UserDto userOut = userMapper.toDto(user);
		userOut.setToken(jwtTokenUtil.generateToken(user));
		return ResponseEntity.ok(userOut);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) throws Exception {
		return ResponseEntity.ok(userMapper.toDto(userService.create(userMapper.toEntity(user))));
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		int httpCode = AbcExceptionHandler.findHttpStatus(authException).value();
		response.setStatus(httpCode);
		String json = new ObjectMapper().writeValueAsString(new ErrorDto(httpCode, authException.getMessage()));
		response.getWriter().write(json);
		response.flushBuffer();
	}
	
}
