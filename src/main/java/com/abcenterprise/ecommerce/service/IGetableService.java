package com.abcenterprise.ecommerce.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.abcenterprise.ecommerce.model.entity.IGenericEntity;

@Transactional
public interface IGetableService<E extends IGenericEntity> {
	
	public E getById(Long id);
	public List<E> getAll();

}
