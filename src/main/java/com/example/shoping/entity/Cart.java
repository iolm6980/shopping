package com.example.shoping.entity;

import com.querydsl.core.types.Order;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product", "member", "orders"})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    private int amount;
    private String size;
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;

    public void setOrder(Orders orders){
        this.orders = orders;
    }

}
