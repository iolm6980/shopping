package com.example.shoping.service;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
