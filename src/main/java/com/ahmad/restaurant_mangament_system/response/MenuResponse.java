package com.ahmad.restaurant_mangament_system.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer restaurantId;
    private String createdBy;
    private String updatedBy;
}
