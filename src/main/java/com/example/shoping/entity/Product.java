package com.example.shoping.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String name;
    private int price;
    private int likeCount;
    private ProductType type;
    public void changeName(String name){ this.name = name; }
    public void changePrice(int price){ this.price = price; }
    public void changeLikeCount(int likeCount){ this.likeCount = likeCount; }

}
