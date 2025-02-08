package com.ahmad.restaurant_mangament_system.services;

import com.ahmad.restaurant_mangament_system.model.Menu;
import com.ahmad.restaurant_mangament_system.model.MenuItem;
import com.ahmad.restaurant_mangament_system.request.MenuItemRequest;
import com.ahmad.restaurant_mangament_system.request.MenuRequest;

import java.util.List;

public interface MenuService {

    Menu addMenu(MenuRequest menuRequest);

    MenuItem addMenuItem(MenuItemRequest menuItemRequest);

    MenuItem updateMenuItem(MenuItemRequest menuItemRequest);

    void deleteMenu(Integer menuId);

    void clearMenu(Integer menuId);

    void deleteMenuItem(Integer menuItemId);

    List<Menu> browseAllRestaurantMenus(Integer restaurantId);

    List<MenuItem> browseItemsInMenu(Integer menuId);

    List<MenuItem> findMenuItemByTitle(String title);


}
