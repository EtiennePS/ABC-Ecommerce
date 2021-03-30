package com.abcenterprise.ecommerce.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.abcenterprise.ecommerce.model.entity.User;

public interface IUserService extends IGetableService<User>, ICreatableService<User>, UserDetailsService {
	
	public User getByUsername(String username);
	public User getByEmail(String email);
	public User getByUsernameOrEmail(String username);
	
	@Override
    public User loadUserByUsername(String username);
}
