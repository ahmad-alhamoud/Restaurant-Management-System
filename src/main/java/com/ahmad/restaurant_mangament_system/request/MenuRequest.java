package com.ahmad.restaurant_mangament_system.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String name;
    private String description;
    private Integer restaurantId;
    private String createdBy;
    private String updatedBy;

}
