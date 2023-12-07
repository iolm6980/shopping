package com.example.shoping.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductImageDTO {
    private String uuid;
    private String imgName;
    private String path;

}
