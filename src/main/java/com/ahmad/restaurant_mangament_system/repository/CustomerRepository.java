package com.ahmad.restaurant_mangament_system.repository;

import com.ahmad.restaurant_mangament_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
