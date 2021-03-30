package com.abcenterprise.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abcenterprise.ecommerce.model.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	Optional<City> findByName(String name);
	
}
