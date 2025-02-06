package com.ahmad.restaurant_mangament_system.services;

import com.ahmad.restaurant_mangament_system.model.Restaurant;
import com.ahmad.restaurant_mangament_system.request.RestaurantRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    Restaurant addNewRestaurant(RestaurantRequest restaurantRequest);

    Restaurant updateRestaurant(RestaurantRequest restaurantRequest);

    List<Restaurant> listALlRestaurant(Pageable pageable);

    Restaurant getRestaurantRating(Integer restaurantId);

    void deleteRestaurant(Integer restaurantId);
}
