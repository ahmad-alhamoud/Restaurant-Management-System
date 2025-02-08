package com.ahmad.restaurant_mangament_system.services.impl;

import com.ahmad.restaurant_mangament_system.enums.ErrorMessage;
import com.ahmad.restaurant_mangament_system.exception.NotFoundException;
import com.ahmad.restaurant_mangament_system.model.Menu;
import com.ahmad.restaurant_mangament_system.model.MenuItem;
import com.ahmad.restaurant_mangament_system.model.Restaurant;
import com.ahmad.restaurant_mangament_system.repository.MenuItemRepository;
import com.ahmad.restaurant_mangament_system.repository.MenuRepository;
import com.ahmad.restaurant_mangament_system.repository.RestaurantRepository;
import com.ahmad.restaurant_mangament_system.request.MenuItemRequest;
import com.ahmad.restaurant_mangament_system.request.MenuRequest;
import com.ahmad.restaurant_mangament_system.services.MenuService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final LocalDateTime dateTime = LocalDateTime.now();

    @Transactional
    @Override
    public Menu addMenu(MenuRequest menuRequest) {
        // TODO : Should not Send the id
        // isMenuExists(menuRequest.getId());
        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestaurantId()).get();
        Menu menu = buildMenu(menuRequest);
        menu.setRestaurant(restaurant);
        menu.setCreationTime(dateTime);
        menu.setCreatedBy(menuRequest.getCreatedBy());
        menu.setCreatedBy(menuRequest.getUpdatedBy());

        return menuRepository.save(menu);
    }

    Menu buildMenu(MenuRequest menuRequest) {
        return Menu.builder()
                // .id(menuRequest.getId())
                .name(menuRequest.getName())
                .description(menuRequest.getDescription())
                .build();
    }

    @Transactional
    @Override
    public MenuItem addMenuItem(MenuItemRequest menuItemRequest) {
        isMenuItemExists(menuItemRequest.getId());
        Menu menu = menuRepository.findById(menuItemRequest.getMenuId()).get();
        MenuItem menuItem = buildMenuItem(menuItemRequest);
        menuItem.setMenu(menu);
        menuItem.setCreationTime(dateTime);
        menuItem.setCreatedBy(menuItemRequest.getCreatedBy());
        menuItem.setUpdatedBy(menuItemRequest.getUpdatedBy());

        return menuItemRepository.save(menuItem);
    }

    private MenuItem buildMenuItem(MenuItemRequest menuItemRequest) {
        return MenuItem.builder()
                .id(menuItemRequest.getId())
                .title(menuItemRequest.getTitle())
                .price(menuItemRequest.getPrice())
                .ingredients(menuItemRequest.getIngredients())
                .quantity(menuItemRequest.getQuantity())
                .build();
    }

    @Transactional
    @Override
    public MenuItem updateMenuItem(MenuItemRequest menuItemRequest) {
        isMenuExists(menuItemRequest.getMenuId());
        Menu menu = menuRepository.findById(menuItemRequest.getMenuId()).get();
        MenuItem updatedMenuItem = buildMenuItem(menuItemRequest);
        updatedMenuItem.setLastUpdatedTime(dateTime);
        return menuItemRepository.save(updatedMenuItem);
    }

    @Override
    public void deleteMenu(Integer menuId) {
        clearMenu(menuId);
        Menu menu = menuRepository.findById(menuId).get();
        menuRepository.delete(menu);
    }

    @Override
    public void clearMenu(Integer menuId) {
        isMenuExists(menuId);
        Menu menu = menuRepository.findById(menuId).get();
        menuItemRepository.deleteMenuItemsByMenu(menu);
    }

    @Override
    public void deleteMenuItem(Integer menuItemId) {
        isMenuItemExists(menuItemId);
        MenuItem menuItem = menuItemRepository.findById(menuItemId).get();
        menuItemRepository.delete(menuItem);
    }

    @Override
    public List<Menu> browseAllRestaurantMenus(Integer restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<MenuItem> browseItemsInMenu(Integer menuId) {
        isMenuNotExists(menuId);
        return menuItemRepository.findAllByMenuId(menuId);
    }

    @Override
    public List<MenuItem> findMenuItemByTitle(String title) {
        isMeunItemNotExists(title);
        return menuItemRepository.findByTitleContaining(title);
    }

    public void isMeunItemNotExists(String title) {
        List<MenuItem> menuItems = menuItemRepository.findByTitleContaining(title);
        if (menuItems.isEmpty())
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), ErrorMessage.MENU_ITEM_NOT_FOUND.name());
    }

    public void isMenuItemExists(Integer menuItemId) {
        if (menuItemRepository.findById(menuItemId).isPresent()) {
            throw new NotFoundException(HttpStatus.FORBIDDEN.value(), ErrorMessage.MENU_ALREADY_EXISTS.name());
        }
    }

    public void isMenuNotItemExists(Integer menuItemId) {
        if (menuItemRepository.findById(menuItemId).isEmpty()) {
            throw new NotFoundException(HttpStatus.FORBIDDEN.value(), ErrorMessage.MENU_ITEM_NOT_FOUND.name());
        }
    }

    public void isMenuExists(Integer menuId) {
        if (menuRepository.findById(menuId).isPresent()) {
            throw new NotFoundException(HttpStatus.FORBIDDEN.value(), ErrorMessage.MENU_ALREADY_EXISTS.getMessage());
        }
    }

    public void isMenuNotExists(Integer menuId) {
        if (menuRepository.findById(menuId).isEmpty()) {
            throw new NotFoundException(HttpStatus.FORBIDDEN.value(), ErrorMessage.MENU_NOT_FOUND.name());
        }
    }
}
