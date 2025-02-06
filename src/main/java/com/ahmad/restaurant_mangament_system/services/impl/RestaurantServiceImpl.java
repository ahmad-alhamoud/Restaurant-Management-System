package com.ahmad.restaurant_mangament_system.services.impl;

import com.ahmad.restaurant_mangament_system.enums.ErrorMessage;
import com.ahmad.restaurant_mangament_system.enums.Status;
import com.ahmad.restaurant_mangament_system.exception.NotFoundException;
import com.ahmad.restaurant_mangament_system.model.Restaurant;
import com.ahmad.restaurant_mangament_system.repository.RestaurantRepository;
import com.ahmad.restaurant_mangament_system.request.RestaurantRequest;
import com.ahmad.restaurant_mangament_system.services.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public Restaurant addNewRestaurant(RestaurantRequest restaurantRequest) {
        isRestaurantExists(restaurantRequest.getName());
        Restaurant restaurant = buildRestaurant(restaurantRequest);
        restaurant.setCreationTime(dateTime);

        return restaurantRepository.save(restaurant);
    }

    Restaurant buildRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = Restaurant.builder()
                .id(restaurantRequest.getId())
                .name(restaurantRequest.getName())
                .description(restaurantRequest.getDescription())
                .location(restaurantRequest.getLocation())
                .openingTime(restaurantRequest.getOpeningTime())
                .closingTime(restaurantRequest.getClosingTime())
                .restaurantStatus(restaurantRequest.getStatus())
                .build();

        restaurant.setCreatedBy(restaurantRequest.getCreatedBy());
        restaurant.setUpdatedBy(restaurantRequest.getUpdatedBy());

        return restaurant;
    }

    @Override
    @Transactional
    public Restaurant updateRestaurant(RestaurantRequest restaurantRequest) {
        isRestaurantNotExists(restaurantRequest.getId());
        Restaurant restaurant = buildRestaurant(restaurantRequest);
        restaurant.setLastUpdatedTime(dateTime);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> listALlRestaurant(Pageable pageable) {
        return restaurantRepository.findAllByRestaurantStatus(Status.ACTIVE, pageable);
    }

    @Override
    public Restaurant getRestaurantRating(Integer restaurantId) {
        return null;
    }

    @Override
    @Transactional
    public void deleteRestaurant(Integer restaurantId) {
        isRestaurantNotExists(restaurantId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        restaurant.setRestaurantStatus(Status.DELETED);
        restaurantRepository.save(restaurant);
    }

    public void isRestaurantExists(String restaurantName) {
        if (restaurantRepository.findByName(restaurantName).isPresent()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), ErrorMessage.RESTAURANT_ALREADY_EXISTS.getMessage());
        }
    }

    public void isRestaurantNotExists(Integer restaurantId) {
        if (restaurantRepository.findById(restaurantId).isPresent()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), ErrorMessage.RESTAURANT_NOT_FOUND.getMessage());
        }
    }

}
