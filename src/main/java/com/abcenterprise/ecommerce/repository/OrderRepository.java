package com.abcenterprise.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abcenterprise.ecommerce.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
