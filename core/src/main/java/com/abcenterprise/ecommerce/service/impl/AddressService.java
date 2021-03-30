package com.abcenterprise.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcenterprise.ecommerce.model.entity.Address;
import com.abcenterprise.ecommerce.repository.AddressRepository;
import com.abcenterprise.ecommerce.service.IAddressService;

@Service
public class AddressService extends GetableService<Address> implements IAddressService {
	
	@Autowired
	protected AddressRepository repository;

	@Override
	public Address create(Address c) {		
		return repository.save(c);
	}	
}
