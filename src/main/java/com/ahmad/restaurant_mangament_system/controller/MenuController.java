package com.ahmad.restaurant_mangament_system.controller;

import com.ahmad.restaurant_mangament_system.request.MenuItemRequest;
import com.ahmad.restaurant_mangament_system.request.MenuRequest;
import com.ahmad.restaurant_mangament_system.response.MenuItemResponse;
import com.ahmad.restaurant_mangament_system.response.MenuResponse;
import com.ahmad.restaurant_mangament_system.services.MenuService;
import com.ahmad.restaurant_mangament_system.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MapperUtil mapper;

    @PostMapping("/create")
    public ResponseEntity<MenuResponse> addMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = mapper.mapEntity(menuService.addMenu(menuRequest), MenuResponse.class);
        return new ResponseEntity<>(menuResponse, HttpStatus.CREATED);
    }

    @PostMapping("/create/item")
    public ResponseEntity<MenuItemResponse> addMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        MenuItemResponse menuItemResponse = mapper.mapEntity(menuService.addMenuItem(menuItemRequest), MenuItemResponse.class);
        return new ResponseEntity<>(menuItemResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/create/item")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        MenuItemResponse response = mapper.mapEntity(menuService.updateMenuItem(menuItemRequest), MenuItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/listItems/{menuId}")
    public ResponseEntity<List<MenuItemResponse>> browseItemsInMenu(@PathVariable Integer menuId) {
        List<MenuItemResponse> responseList = mapper.mapList(menuService.browseItemsInMenu(menuId), MenuItemResponse.class);
        return new ResponseEntity<>(responseList, FOUND);
    }

    @GetMapping("/listMenus/{restaurantId}")
    public ResponseEntity<List<MenuResponse>> listAllRestaurantMenus(@PathVariable Integer restaurantId) {
        List<MenuResponse> responseList = mapper.mapList(menuService.browseAllRestaurantMenus(restaurantId), MenuResponse.class);
        return new ResponseEntity<>(responseList, FOUND);
    }

    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>("menu with id '" + menuId
                + "' is deleted successfully", OK);
    }

    @DeleteMapping("/clear/{menuId}")
    public ResponseEntity<String> clearMenu(@PathVariable Integer menuId) {
        menuService.clearMenu(menuId);
        return new ResponseEntity<>("menu with id '" + menuId
                + "' is cleared successfully", OK);
    }

    @DeleteMapping("/delete/item/{menuItemId}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Integer menuItemId) {
        menuService.deleteMenuItem(menuItemId);
        return new ResponseEntity<>("Item with id '" + menuItemId
                + "' is deleted successfully", OK);
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<List<MenuItemResponse>> findMenuItemByTitle(@RequestParam String title) {
        List<MenuItemResponse> responseList = mapper.mapList(menuService.findMenuItemByTitle(title), MenuItemResponse.class);
        return new ResponseEntity<>(responseList, OK);
    }
}
