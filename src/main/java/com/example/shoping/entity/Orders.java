package com.example.shoping.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = { "cartList"})
public class Orders extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;
    private int totalPrice;
    private String orderName;

    @Builder.Default
    @OneToMany(mappedBy = "orders")
    private List<Cart> cartList = new ArrayList<>();

}
