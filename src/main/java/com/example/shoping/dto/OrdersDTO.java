package com.example.shoping.dto;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Long ono;
    private Long amount;
    private String productSize;
    private ProductDTO productDTO;
    private SellerDTO sellerDTO;
    private MemberDTO memberDTO;

}
