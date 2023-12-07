package com.example.shoping.service;

import com.example.shoping.dto.OrdersDTO;
import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
@SpringBootTest
public class OrdersServiceTests {
    @Autowired
    private OrdersService ordersService;

    @Test
    public void buyListTest(){
    }

    @Test
    public void deleteOrder(){
    }

    @Test
    public void orderListTest(){
        List<OrdersDTO> result = ordersService.getOrderList("testId1");
        System.out.println(result);

    }
}
