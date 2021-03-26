package com.abcenterprise.ecommerce.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abcenterprise.ecommerce.exception.NoSuchEntityException;
import com.abcenterprise.ecommerce.model.entity.IGenericEntity;
import com.abcenterprise.ecommerce.service.IGetableService;

public abstract class GetableService<E extends IGenericEntity> implements IGetableService<E> {
	
	@Autowired
	protected JpaRepository<E, Long> repository;

	@Override
	public E getById(Long id) {
		
		try {
			return repository.findById(id).get();
		}
		catch(NoSuchElementException e) {
			throw new NoSuchEntityException("There is no " + getEntityClassName() + " at the id " + id, e);
		}
	}

	@Override
	public List<E> getAll() {
		return repository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	private String getEntityClassName() {
		return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
	}
}
