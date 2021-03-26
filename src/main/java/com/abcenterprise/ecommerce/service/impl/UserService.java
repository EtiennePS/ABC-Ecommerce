package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.abcenterprise.ecommerce.exception.IllegalEntityException;
import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.Role;
import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.repository.UserRepository;
import com.abcenterprise.ecommerce.service.IRoleService;
import com.abcenterprise.ecommerce.service.IUserService;
@Service
public class UserService extends GetableService<User> implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User create(User u) {
		String password = u.getPassword();
		
		if(StringUtils.hasLength(u.getUsername())) 
			throw new IllegalEntityException("Username is mandatory!");
		
		if(StringUtils.hasLength(u.getEmail()))
			throw new IllegalEntityException("Email is mandatory!");
		
		if(StringUtils.hasLength(password))
			throw new IllegalEntityException("Password is mandatory!");
		
		u.setPassword(passwordEncoder.encode(password));
		u.setAllRoles(roleService.getByName(Role.CUSTOMER));
		u.setEnabled(true);
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCredentialsNonExpired(true);
		
		return repository.save(u);
	}
	
	@Override
    public User loadUserByUsername(String username) {
        return getByUsernameOrEmail(username);
    }
	
	public User getByUsernameOrEmail(String login) {
		User user = login != null && login.contains("@") ? repository.findByEmail(login) : repository.findByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username or email : " + login);
        }
        return user;
	}
	
	public User getByUsername(String username) {
		User user = repository.findByUsername(username);
        if (user == null) {
            throw new NoSuchEntityException("No user present with username : " + username);
        }
        return user;
	}
	
	public User getByEmail(String email) {
		User user = repository.findByEmail(email);
        if (user == null) {
            throw new NoSuchEntityException("No user present with email : " + email);
        }
        return user;
	}
}
