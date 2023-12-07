package com.example.shoping.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "product")
public class ProductImage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;
    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;



}
