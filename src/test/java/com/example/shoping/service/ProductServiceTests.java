package com.example.shoping.service;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @Test
    public void getList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setSize(5);
        PageResultDTO result = productService.getProductList(pageRequestDTO);
        System.out.println(result);
    }

    @Test
    public void getProduct(){
        System.out.println(productService.getProduct(2L));
    }
    @Test
    public void removeProduct(){
        productService.removeProduct(100L);
    }
    @Test
    public void modifyTest(){
        ProductDTO productDTO = ProductDTO.builder()
                .pno(3L)
                .name("modifyProduct2")
                .price(2)
                .likeCount(2)
                .type("PANTS")
                .build();

        productService.modifyProduct(productDTO);
    }

}
