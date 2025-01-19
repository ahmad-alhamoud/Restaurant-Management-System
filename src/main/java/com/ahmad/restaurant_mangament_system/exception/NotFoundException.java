package com.ahmad.restaurant_mangament_system.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 248611599356373897L;
    private Integer code;
    private String message;
}
