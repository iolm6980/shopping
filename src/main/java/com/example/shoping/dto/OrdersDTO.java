package com.example.shoping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Long ono;
    private String orderName;
    private int totalPrice;
    private List<CartDTO> cartList;

}
