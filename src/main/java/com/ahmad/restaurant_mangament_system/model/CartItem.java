package com.ahmad.restaurant_mangament_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "cart_id")
    private Cart cart;
}
