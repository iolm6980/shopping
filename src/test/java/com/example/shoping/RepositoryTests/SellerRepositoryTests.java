package com.example.shoping.RepositoryTests;

import com.example.shoping.entity.Seller;
import com.example.shoping.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class SellerRepositoryTests {
    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void insertSeller(){
        IntStream.rangeClosed(1,30).forEach(i->{
            Seller seller = Seller.builder().name("seller"+i).build();
            sellerRepository.save(seller);
        });
    }
}
