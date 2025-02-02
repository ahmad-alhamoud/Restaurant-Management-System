package com.ahmad.restaurant_mangament_system.repository;

import com.ahmad.restaurant_mangament_system.model.Cart;
import com.ahmad.restaurant_mangament_system.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart(Cart cart);
}
