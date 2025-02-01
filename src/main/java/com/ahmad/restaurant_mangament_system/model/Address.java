package com.ahmad.restaurant_mangament_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "state")
    private String state;

    @Column(name = "government")
    private String government;

    @Column(name = "contact_number")
    private String contactNumber;
}
