package com.example.shoping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cno;
    private int amount;
    private String size;
    private ProductDTO productDTO;
    private MemberDTO memberDTO;

}
