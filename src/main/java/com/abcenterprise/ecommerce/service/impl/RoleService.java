package com.abcenterprise.ecommerce.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.Privilege;
import com.abcenterprise.ecommerce.model.entity.Role;
import com.abcenterprise.ecommerce.repository.RoleRepository;
import com.abcenterprise.ecommerce.service.IRoleService;

@Service
public class RoleService extends GetableService<Role> implements IRoleService {
	@Autowired
	protected RoleRepository repository;
	
	@Transactional
    public Role findOrCreate(String name, Collection<Privilege> privileges) {
        Optional<Role> rp = repository.findByName(name);
        return rp.isPresent() ? rp.get() : repository.save(new Role(name, privileges));
    }
	
	@Transactional
    public Role findOrCreate(String name, Privilege... privileges) {
        return this.findOrCreate(name, Arrays.asList(privileges));
    }
	
	public Role getByName(String name) {
		
		try {
			return this.repository.findByName(name).get();
		}
		catch(NoSuchElementException e) {
			throw new NoSuchEntityException("There is no role with the name " + name, e);
		}
	}
}
