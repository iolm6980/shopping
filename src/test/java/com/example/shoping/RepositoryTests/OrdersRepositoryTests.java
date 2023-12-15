package com.example.shoping.RepositoryTests;

import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.repository.CartRepository;
import com.example.shoping.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootTest
public class OrdersRepositoryTests {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void saveTest(){
        List<Cart> list = cartRepository.findAll();

        System.out.println(list);
        Orders orders = Orders.builder()
                .orderName("testOrder")
                .totalPrice(12000)
                .cartList(list)
                .build();
        ordersRepository.save(orders);

        System.out.println(orders);
    }

    @Test
    public void getOrders(){
        List<Object[]> list = ordersRepository.getOrders();

        for(Object[] objects : list){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void deleteTest(){
        ordersRepository.deleteById(2L);
    }

}
