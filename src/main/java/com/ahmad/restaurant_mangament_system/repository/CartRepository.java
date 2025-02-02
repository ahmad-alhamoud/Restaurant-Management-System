package com.ahmad.restaurant_mangament_system.repository;

import com.ahmad.restaurant_mangament_system.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByCustomerId(Integer custId);
}
