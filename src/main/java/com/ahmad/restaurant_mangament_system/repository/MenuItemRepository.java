package com.ahmad.restaurant_mangament_system.repository;

import com.ahmad.restaurant_mangament_system.model.Menu;
import com.ahmad.restaurant_mangament_system.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    void deleteMenuItemsByMenu(Menu menu);

    List<MenuItem> findAllByMenuId(Integer menuId);

    List<MenuItem> findByTitleContaining(String title);
}
