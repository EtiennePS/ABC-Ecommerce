package com.abcenterprise.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abcenterprise.ecommerce.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Optional<Item> findByName(String name);
	
}
