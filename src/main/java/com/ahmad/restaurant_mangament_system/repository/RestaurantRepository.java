package com.ahmad.restaurant_mangament_system.repository;

import com.ahmad.restaurant_mangament_system.enums.Status;
import com.ahmad.restaurant_mangament_system.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findAllByRestaurantStatus(Status status, Pageable pageable);

    Optional<Restaurant> findByName(String name);

}
