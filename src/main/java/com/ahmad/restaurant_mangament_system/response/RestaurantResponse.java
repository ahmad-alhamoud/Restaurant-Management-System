package com.ahmad.restaurant_mangament_system.response;

import com.ahmad.restaurant_mangament_system.enums.Status;
import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {
    private Integer id;
    private String name;
    private String description;
    private Status status;
    private String location;
    private String createdBy;
    private String updatedBy;
    private LocalTime OpeningTime;
    private LocalTime ClosingTime;
}
