package com.ahmad.restaurant_mangament_system.controller;

import com.ahmad.restaurant_mangament_system.request.RestaurantRequest;
import com.ahmad.restaurant_mangament_system.response.RestaurantResponse;
import com.ahmad.restaurant_mangament_system.services.RestaurantService;
import com.ahmad.restaurant_mangament_system.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final MapperUtil mapper;

    @PostMapping("/create")
    public ResponseEntity<RestaurantResponse> addNewRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        RestaurantResponse response = mapper.mapEntity(restaurantService.addNewRestaurant(restaurantRequest), RestaurantResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@RequestBody RestaurantRequest request) {
        RestaurantResponse response = mapper.mapEntity(restaurantService.updateRestaurant(request), RestaurantResponse.class);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponse>> listAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<RestaurantResponse> responseList = mapper.mapList(restaurantService.listALlRestaurant(pageable), RestaurantResponse.class);
        return new ResponseEntity<>(responseList, HttpStatus.FOUND);
    }

    @PutMapping("/delete/{restaurantId}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>("restaurant with id " + restaurantId + "deleted successfully", HttpStatus.ACCEPTED);
    }


}
