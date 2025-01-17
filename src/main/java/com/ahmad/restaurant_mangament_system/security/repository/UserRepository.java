package com.ahmad.restaurant_mangament_system.security.repository;

import com.ahmad.restaurant_mangament_system.security.enums.Role;
import com.ahmad.restaurant_mangament_system.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
