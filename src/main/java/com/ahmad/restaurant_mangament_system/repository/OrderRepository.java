package com.ahmad.restaurant_mangament_system.repository;

import com.ahmad.restaurant_mangament_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
