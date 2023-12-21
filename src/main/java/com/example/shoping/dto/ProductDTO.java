package com.example.shoping.dto;

import com.example.shoping.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long pno;
    private String name;
    private int price;
    private int likeCount;
    private ProductType type;
    @Builder.Default
    private List<ProductImageDTO> productImageList = new ArrayList<>();

}
