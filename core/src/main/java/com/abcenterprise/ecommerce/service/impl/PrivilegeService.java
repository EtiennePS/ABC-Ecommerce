package com.abcenterprise.ecommerce.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.Privilege;
import com.abcenterprise.ecommerce.repository.PrivilegeRepository;
import com.abcenterprise.ecommerce.service.IPrivilegeService;

@Service
public class PrivilegeService extends GetableService<Privilege> implements IPrivilegeService {
	@Autowired
	protected PrivilegeRepository repository;
	
	@Transactional
    public Privilege findOrCreate(String name) {
        Optional<Privilege> op = repository.findByName(name);
        return op.isPresent() ? op.get() : repository.save(new Privilege(name));
    }
	
	public Privilege getByName(String name) {
		
		try {
			return this.repository.findByName(name).get();
		}
		catch(NoSuchElementException e) {
			throw new NoSuchEntityException("There is no role with the name " + name, e);
		}
	}
}
