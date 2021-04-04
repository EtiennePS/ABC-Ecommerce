package com.abcenterprise.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abcenterprise.ecommerce.model.entity.Order;
import com.abcenterprise.ecommerce.model.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByUser(User user);
}
