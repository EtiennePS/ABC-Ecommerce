package com.abcenterprise.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abcenterprise.ecommerce.model.entity.CartLine;
import com.abcenterprise.ecommerce.model.entity.User;

@Repository
public interface CartLineRepository extends JpaRepository<CartLine, Long> {
	
	List<CartLine> findAllByUser(User user);

}
