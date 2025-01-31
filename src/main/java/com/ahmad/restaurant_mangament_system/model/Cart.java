package com.ahmad.restaurant_mangament_system.model;

import com.ahmad.restaurant_mangament_system.enums.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_items")
    private Integer totalItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "cart_status")
    private CartStatus status;

    @Column(name = "discount")
    private Double discount;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems;


}
