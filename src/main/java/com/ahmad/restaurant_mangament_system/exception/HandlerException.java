package com.ahmad.restaurant_mangament_system.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class HandlerException extends RuntimeException {
    private static final long serialVersionUID = 248611599356373897L;
    private String message;
}
