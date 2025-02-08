package com.ahmad.restaurant_mangament_system.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {

    private Integer id;
    private String title;
    private String ingredients;
    private Double price;
    private Integer quantity;
    private Integer menuId;
    private String createdBy;
    private String updatedBy;
}
