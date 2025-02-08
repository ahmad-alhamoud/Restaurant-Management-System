package com.ahmad.restaurant_mangament_system.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponse {
    private Integer id;
    private String title;
    private String ingredients;
    private Double price;
    private Integer quantity;
    private Integer menuId;
}
