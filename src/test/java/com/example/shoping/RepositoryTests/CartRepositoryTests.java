package com.example.shoping.RepositoryTests;

import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class CartRepositoryTests {
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void insertTest(){
        IntStream.rangeClosed(1,3).forEach(i ->{
            Product product = Product.builder().pno(Long.valueOf(i)).build();
            Member member = Member.builder().userId("testId1").build();
            Cart cart = Cart.builder()
                    .amount(1)
                    .size("S")
                    .member(member)
                    .product(product)
                    .build();
            cartRepository.save(cart);
        });
    }
    @Test
    public void setCart(){
        List<Cart> list = cartRepository.findAll();
        Orders orders = Orders.builder().ono(1L).build();
        for (Cart cart : list) {
            cartRepository.updateCartOrder(cart.getCno(), orders);
        }
    }

    @Test
    public void getCart(){
        List<Long> a = Arrays.asList(20L, 21L);
        List<Cart> list = cartRepository.findAll();
        System.out.println(list);
    }
}
