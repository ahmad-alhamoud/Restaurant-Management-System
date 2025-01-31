package com.ahmad.restaurant_mangament_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Orders")
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
