package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.ProductImage;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public interface ProductService {
    PageResultDTO<ProductDTO, Object[]> getProductList(PageRequestDTO pageRequestDTO);
    ProductDTO getProduct(Long pno);
    void removeProduct(Long pno);
    void modifyProduct(ProductDTO productDTO);
    List<Product> test();
    default Product dtoToEntity(ProductDTO productDTO){
        Product product = Product.builder()
                .pno(productDTO.getPno())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .likeCount(productDTO.getLikeCount())
                .type(productDTO.getType())
                .build();
        return product;
    }

    default ProductDTO entityToDTO(Product product, List<ProductImage> productImageList){
        List<ProductImageDTO> productImageDTO = productImageList.stream().map(productImage -> {
            return ProductImageDTO.builder()
                    .uuid(productImage.getUuid())
                    .path(productImage.getPath())
                    .imgName(productImage.getImgName())
                    .build();
        }).collect(Collectors.toList());

        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .name(product.getName())
                .price(product.getPrice())
                .likeCount(product.getLikeCount())
                .type(product.getType())
                .productImageList(productImageDTO)
                .build();

        return productDTO;
    }

}
