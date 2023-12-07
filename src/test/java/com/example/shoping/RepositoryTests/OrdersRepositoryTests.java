package com.example.shoping.RepositoryTests;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.Seller;
import com.example.shoping.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.List;

@SpringBootTest
public class OrdersRepositoryTests {
    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void saveTest(){
        IntStream.rangeClosed(1,10).forEach(i -> {
            Seller seller = Seller.builder().sno(Long.valueOf(i)).build();
            Member member = Member.builder().userId("testId1").build();
            Product product = Product.builder().pno(Long.valueOf(i)).build();

            Orders orders = Orders.builder()
                    .amount(2L)
                    .productSize("L")
                    .member(member)
                    .product(product)
                    .seller(seller)
                    .build();

            ordersRepository.save(orders);
        });
    }


    @Test
    public void test(){
    }
}
